package rs.fon.queue.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.fon.queue.blogic.exception.InsertionDisabledException;
import rs.fon.queue.blogic.exception.StudentAlreadyExistException;
import rs.fon.queue.blogic.impl.Student;
import rs.fon.queue.blogic.util.Constants;
import rs.fon.queue.model.StandModel;
import rs.fon.queue.model.StudentModel;
import rs.fon.queue.service.FacultyService;

@Controller
public class StudentController {

	
	@Autowired
	private FacultyService facultyService;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String studentPage(Model model, 
			                  HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("student", "student");
		
		
		StandModel stand_1 = getStanModel(1);
		StandModel stand_2 = getStanModel(2);
		StandModel stand_3 = getStanModel(3);
		model.addAttribute("stand_1",stand_1);
		model.addAttribute("stand_2", stand_2);
		model.addAttribute("stand_3", stand_3);
		model.addAttribute("studentModel", new StudentModel());
		return "student";
	}
	
	
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String insertStudent(Model model,
								@Valid @ModelAttribute("studentModel") StudentModel studentModel,
								BindingResult bindingResult,
								HttpServletRequest request) {
		String message = null;
		
		HttpSession session = request.getSession(false);
		
		if (session == null)
			message = "Sesija više ne postoji, pokušajte da osvežite stranicu.";
		else { 
			//sesija postoji
			String sessionAttr = (String) session.getAttribute("student");
			if (sessionAttr == null || !sessionAttr.equals("student"))
				message = "Sesija više ne postoji, pokušajte da osvežite stranicu.";
			else { 
				// sve je u redu sa konekcijom 
				if (!bindingResult.hasErrors()) {
					//sve je ok sa parametrima 
					
					//dvostruka provera na spam
					if (studentModel.getAntiSpam() != null && studentModel.getAntiSpam().length() != 0)
						message = "Greška, ako nisi robot ostavi prazno.";
					else {
						//nije robot
						//provera korisnih parametara
						String index = studentModel.getIndexNumber();
						String stand = studentModel.getStandNumber();
						if (checkIndex(index) && checkStandNumber(stand)) {
							//sve je u redu sa parametrima
							if (stand != null  &&  !stand.isEmpty()) {
								//u pitanju je ubacivanje
								int standNumber = Integer.parseInt(stand);
								try {
									getFacultyService().insertStudent(index, standNumber);
									message = "Uspešno ste se prijavili u red za šalter.";
								} catch (StudentAlreadyExistException e) {
									message = "Već se nalazite u redu za taj šalter.";
								} catch (InsertionDisabledException e) {
									message = "Prijavljivanje na šalter trenutno nije moguće.";
								}
							}	
							// svakako uradi proveru za studenta
							String ordNum_1 = getOrdNum(index, 1);
							String ordNum_2 = getOrdNum(index, 2);
							String ordNum_3 = getOrdNum(index, 2);
							model.addAttribute("ordNum_1", ordNum_1);
							model.addAttribute("ordNum_2", ordNum_2);
							model.addAttribute("ordNum_3", ordNum_3);
							
						}
						else {
							//greska u unosu parametara
							message = "Pogrešno uneti parametri.";
						}	
					}
				} else {
					message = "Pogrešno uneti parametri.";
				}
			}
		}

		StandModel stand_1 = getStanModel(1);
		StandModel stand_2 = getStanModel(2);
		StandModel stand_3 = getStanModel(3);
		model.addAttribute("stand_1",stand_1);
		model.addAttribute("stand_2", stand_2);
		model.addAttribute("stand_3", stand_3);
		model.addAttribute("message", message);
		return "student";
	}
	
	
	
	
	
	
	
	
	
	
	private String getOrdNum(String index, int standNumber) {
		Student student = getFacultyService().findInStand(index, standNumber);
		if (student == null)
			return "#";
		return student.getOrdNum()+"";
	}



	private boolean checkIndex(String index) {
		Pattern p = Pattern.compile("[0-9][0-9][0-9][0-9]/[0-9][0-9][0-9][0-9]");
		return p.matcher(index).matches();
	}



	private boolean checkStandNumber(String stand) {
		if (stand == null || stand.isEmpty())
			return true;
		int standInt;
		try {
			standInt = Integer.parseInt(stand);
		} catch (NumberFormatException e) {
			return false;
		}
		if (standInt < 1 || standInt > Constants.NUMBER_OF_STANDS)
			return false;
		return true;
	}



	private StandModel getStanModel(int standNumber) {
		StandModel standModel = new StandModel();
		
		boolean isOpen = getFacultyService().isStandOpen(standNumber);
		if (isOpen)
			standModel.setOpen("OTVOREN");
		else
			standModel.setOpen("ZATVOREN");
		
		Student student = getFacultyService().getNextStudent(standNumber);
		if (student == null) {
			standModel.setNextIndex("#");
			standModel.setOrdNum("#");
		}
		else {
			standModel.setNextIndex(student.getIndex());
			standModel.setOrdNum(student.getOrdNum()+"");
		}	
		
		standModel.setLength(facultyService.getQueueSize(standNumber)+"");
		
		return standModel;
	}
	
	
	
	
	
	public FacultyService getFacultyService() {
		return facultyService;
	}
	public void setFacultyService(FacultyService facultyService) {
		this.facultyService = facultyService;
	}
	
	
	
	
	
	
	
}
