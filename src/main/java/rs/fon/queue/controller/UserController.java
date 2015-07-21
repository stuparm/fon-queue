package rs.fon.queue.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rs.fon.queue.blogic.impl.Student;
import rs.fon.queue.blogic.util.Action;
import rs.fon.queue.domain.User;
import rs.fon.queue.model.InformationModel;
import rs.fon.queue.model.NextModel;
import rs.fon.queue.model.OpenModel;
import rs.fon.queue.model.UserModel;
import rs.fon.queue.service.FacultyService;

@Controller
public class UserController {

	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String userLogin(Model model, Principal principal) {
		//user model
		String username = principal.getName();
		UserModel userModel = createUserModel(username);
		int standNumber = userModel.getStandNumber();
		InformationModel infoModel = createInformationModel(standNumber);
		
		//adding to the model
		model.addAttribute("informationModel",infoModel);
		model.addAttribute("userModel", userModel);		
		model.addAttribute("openModel", new OpenModel());
		model.addAttribute("nextModel", new NextModel());
		return "user-page";
	}
	
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String open(Model model, Principal principal, 
			@RequestParam(value="action", required=true)Action action) {
		
		
		String username = principal.getName();
		UserModel userModel = createUserModel(username);
		int standNumber = userModel.getStandNumber();


		//Action.OPEN
		String message = null;
		if (action == Action.OPEN) {
			boolean success = getFacultyService().openStand(standNumber);
			if (success)
				message = "Uspesno ste otvorili salter.";
			else
				message = "Salter je vec otvoren.";
		}
		//Action.CLOSE
		if (action == Action.CLOSE) {
			boolean success = getFacultyService().closeStand(standNumber);
			if (success)
				message = "Uspesno ste zatvorili salter.";
			else
				message = "Salter je vec zatvoren.";
		}	
		//
		if (action == Action.NEXT_STUDENT) {
			if (getFacultyService().isStandOpen(standNumber))
				getFacultyService().removeFirstStudent(standNumber);
			else
				message="Morate prvo otvoriti salter.";
		}
		if (action == Action.ENABLE_INSERT) {
			if (getFacultyService().isInsertable(standNumber))
				message = "Prijavljivanje studenata u red je vec omoguceno.";
			else {
				getFacultyService().enableInsertion(standNumber);
				message = "Omoguceno je prijavljivanje studenata u red za salter.";
			}
		}
		if (action == Action.DISABLE_INSERT) {
			if (getFacultyService().isInsertable(standNumber)) {
				getFacultyService().disableInsertion(standNumber);
				message = "Nije vise omoguceno prijavljivanje studenata u red za salter.";
			}
			else {
				message = "Prijavljivanje studenata u red je vec onemoguceno.";
			}
		}
		
		
		
		InformationModel infoModel = createInformationModel(standNumber);
		//adding to the model	
		model.addAttribute("informationModel",infoModel);
		model.addAttribute("message", message);
		model.addAttribute("userModel", userModel);
		
		model.addAttribute("openModel", new OpenModel());
		model.addAttribute("nextModel", new NextModel());
		return "user-page";
	}
	
	
	
	private UserModel createUserModel(String username) {
		User user = getFacultyService().findByUsername(username);
		int standNumber = user.getStand().getStand_number();
		UserModel userModel = new UserModel();
		userModel.setUsername(username);
		userModel.setStandNumber(standNumber);
		return userModel;
	}
	
	
	
	private InformationModel createInformationModel(int standNumber) {
		InformationModel infoModel = new InformationModel();
		if (getFacultyService().isStandOpen(standNumber))
			infoModel.setStatus("Salter broj "+ standNumber+" je otvoren.");
		else 
			infoModel.setStatus("Salter broj "+ standNumber+" je zatvoren.");
		
		int queueSize = getFacultyService().getQueueSize(standNumber);
		infoModel.setQueueSize(queueSize);
		
		Student s = getFacultyService().getNextStudent(standNumber);
		if (s == null) {
			infoModel.setNextOrdNumber("#");
			infoModel.setNextStudentIndex("#");
		}
		else { // s != null
			infoModel.setNextOrdNumber(s.getOrdNum()+"");
			infoModel.setNextStudentIndex(s.getIndex());
		}
		return infoModel;
	}
	
	
	
	
	
	
	
	
	
	public FacultyService getFacultyService() {
		return facultyService;
	}
	public void setFacultyService(FacultyService facultyService) {
		this.facultyService = facultyService;
	}
	
}
