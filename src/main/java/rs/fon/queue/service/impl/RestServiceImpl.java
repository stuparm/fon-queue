package rs.fon.queue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.fon.queue.blogic.exception.InsertionDisabledException;
import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.impl.Student;
import rs.fon.queue.json.CheckJson;
import rs.fon.queue.json.InsertJson;
import rs.fon.queue.json.JsonMessage;
import rs.fon.queue.json.MonitorJson;
import rs.fon.queue.json.StandJson;
import rs.fon.queue.json.StudentJson;
import rs.fon.queue.service.FacultyService;
import rs.fon.queue.service.RestService;

@Service
public class RestServiceImpl implements RestService {

	@Autowired
	private FacultyService facultyService;
	
	@Override
	public InsertJson getInsertJsonFor(String studentIndex, int standNumber) {
		InsertJson json = new InsertJson();
		try {
			getFacultyService().insertStudent(studentIndex, standNumber);
			json.setSUCCESS(true);
			json.setMessage(JsonMessage.OK.name());
		} catch (StudentAlreadyExistException e) {
			json.setSUCCESS(false);
			json.setMessage(JsonMessage.ALREADY_EXIST.name());
		} catch (InsertionDisabledException e) {
			json.setSUCCESS(false);
			json.setMessage(JsonMessage.INSERTION_DISABLED.name());
		}
		json.setSTAND_1(new StudentJson(getFacultyService().findInStand(studentIndex, 1)));
		json.setSTAND_2(new StudentJson(getFacultyService().findInStand(studentIndex, 2)));
		json.setSTAND_3(new StudentJson(getFacultyService().findInStand(studentIndex, 3)));
		json.setSTAND_4(new StudentJson(getFacultyService().findInStand(studentIndex, 4)));
		json.setSTAND_5(new StudentJson(getFacultyService().findInStand(studentIndex, 5)));
		return json;
	}
	
	
	@Override
	public CheckJson getCheckingFor(String studentIndex) {
		CheckJson json = new CheckJson();
		json.setSTAND_1(new StudentJson(getFacultyService().findInStand(studentIndex, 1)));
		json.setSTAND_2(new StudentJson(getFacultyService().findInStand(studentIndex, 2)));
		json.setSTAND_3(new StudentJson(getFacultyService().findInStand(studentIndex, 3)));
		json.setSTAND_4(new StudentJson(getFacultyService().findInStand(studentIndex, 4)));
		json.setSTAND_5(new StudentJson(getFacultyService().findInStand(studentIndex, 5)));
		return json;
	}
	
	
	@Override
	public MonitorJson getMonitorJson() {
		MonitorJson json = new MonitorJson();
		json.setSTAND_1(getStandJsonFor(1));
		json.setSTAND_2(getStandJsonFor(2));
		json.setSTAND_3(getStandJsonFor(3));
		json.setSTAND_4(getStandJsonFor(4));
		json.setSTAND_5(getStandJsonFor(5));
		return json;
	}
	
	
	private StandJson getStandJsonFor(int standNumber) {
		StandJson standJson = new StandJson();
		StudentJson studentJson = new StudentJson(getFacultyService().getNextStudent(standNumber));
		standJson.setNEXT_STUDENT(studentJson);
		standJson.setOPEN(getFacultyService().isStandOpen(standNumber));
		standJson.setPRIMARY_Q_LENGTH(getFacultyService().getQueueSize(standNumber)+"");
		standJson.setTEMP_QUEUE(createTempJsonQueue(standNumber));
		return standJson;
	}
	
	
	private List<StudentJson> createTempJsonQueue(int standNumber) {
		List<Student> list = getFacultyService().getTempQueue(standNumber).toList();
		List<StudentJson> jsonList = new ArrayList<StudentJson>();
		for (Student student : list) {
			jsonList.add(new StudentJson(student));
		}
		return jsonList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public FacultyService getFacultyService() {
		return facultyService;
	}
	
	public void setFacultyService(FacultyService facultyService) {
		this.facultyService = facultyService;
	}
	
}
