package rs.fon.queue.controller;



import java.security.Principal;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rs.fon.queue.blogic.exception.AdminAlreadyExistException;
import rs.fon.queue.blogic.exception.EntityDoesNotExistException;
import rs.fon.queue.blogic.exception.UserAlreadyExistException;
import rs.fon.queue.blogic.util.Action;
import rs.fon.queue.blogic.util.Constants;
import rs.fon.queue.model.AdminModel;
import rs.fon.queue.model.CRUDModel;
import rs.fon.queue.model.PropertyModel;
import rs.fon.queue.model.UserModel;
import rs.fon.queue.service.AdminService;
import rs.fon.queue.service.FacultyService;
import rs.fon.queue.service.PropertyService;


@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private PropertyService propertyService;
	
	
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(Model model,
						Principal principal) {
		
		String adminName = principal.getName();
		AdminModel adminModel = getAdminService().findByUsername(adminName);
		if (adminModel == null)
			return "redirect:/j_spring_security_logout";
		
		List<PropertyModel> props = getPropertyService().getDatabaseProperties();
		List<AdminModel> admins = getAdminService().getAdmins();
		List<UserModel> users = getAdminService().getUsers();
		model.addAttribute("crudModel", new CRUDModel());
		model.addAttribute("adminModel",adminModel);
		model.addAttribute("admins", admins);
		model.addAttribute("users", users);
		model.addAttribute("props", props);
		return "admin-page";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.POST)
	public String admin(Model model,
						Principal principal,
						@Valid @ModelAttribute("crudModel") CRUDModel crudModel,
						BindingResult bindingResult,
						@RequestParam(value="action", required=true) Action action) {
		
		String adminName = principal.getName();
		AdminModel adminModel = getAdminService().findByUsername(adminName);
		if (adminModel == null)
			return "redirect:/j_spring_security_logout";
		
		String message = "";
		
		if (!bindingResult.hasErrors()) { // moguce je izvrsiti operaciju
			if (action == Action.CRUD_INSERT_USER) {
				message = crudInsertUser(crudModel);
			}
			else if (action == Action.CRUD_INSERT_ADMIN) {
				message = crudInsertAdmin(crudModel);
			}
			else if (action == Action.CRUD_UPDATE) {
				message = crudUpdate(crudModel);
			}else if (action == Action.CRUD_DELETE) {
				message = crudDelete(crudModel);
			}else if(action == Action.RESET) {
				getFacultyService().resetState();
				message = "Resetovali ste stanja svih šaltera.";
			}
			
		}

		
		List<PropertyModel> props = getPropertyService().getDatabaseProperties();
		List<AdminModel> admins = getAdminService().getAdmins();
		List<UserModel> users = getAdminService().getUsers();
//		model.addAttribute("crudModel", new CRUDModel());
		model.addAttribute("adminModel",adminModel);
		model.addAttribute("admins", admins);
		model.addAttribute("users", users);
		model.addAttribute("props", props);
		model.addAttribute("message", message);
		return "admin-page";
	}
	
	
	
	
	private String crudDelete(CRUDModel crudModel) {
		try {
			getAdminService().delete(crudModel);
		} catch (EntityDoesNotExistException e) {
			return "Korisnik/administrator sa takvim korisničkim imenom i lozinkom ne postoji.";
		} catch (IllegalArgumentException e) {
			return "Nije moguce obristati entitet iz baze.";
		}
		return "Uspešno ste obrisali korisnika/administratora iz baze";
	}

	private String crudUpdate(CRUDModel crudModel) {
		try {
			getAdminService().update(crudModel);
		} catch (EntityDoesNotExistException e) {
			return "Korisnik/administrator sa takvim korisničkim imenom i lozinkom ne postoji.";
		} catch (IllegalArgumentException e) {
			return "Proverite polja koja želite da izmenite.";
		}
		
		return "Uspešno ste izvršili izmene.";
	}

	private String crudInsertAdmin(CRUDModel crudModel) {
		if (!checkValuesAdmin(crudModel))
			return "Neophodno je da popunite polja: ime, prezime, e-mail, telefon da biste uneli novog administratora.";
		try {
			getAdminService().insertAdmin(crudModel);
		} catch (AdminAlreadyExistException e) {
			return "Administrator sa takvim korisničkim imenom već postoji.";
		}
		return "Uspešno ste uneli novog administratora.";
	}

	private boolean checkValuesAdmin(CRUDModel crudModel) {
		if (crudModel.getFirstName() == null || crudModel.getFirstName().length() == 0)
			return false;
		if (crudModel.getLastName() == null || crudModel.getLastName().length() == 0)
			return false;
		if (crudModel.getEmail() == null || crudModel.getEmail().length() == 0)
			return false;
		if (crudModel.getTelephone() == null || crudModel.getTelephone().length() == 0)
			return false;
		return true;
	}

	private String crudInsertUser(CRUDModel crudModel) {
		if (!checkStand(crudModel.getStandNumber()))
			return "Broj šaltera mora biti u opsegu 1-"+Constants.NUMBER_OF_STANDS+".";
		try {
			getAdminService().insertUser(crudModel);			
		} catch (UserAlreadyExistException e) {
			return "Korisnik sa takvim korisničkim imenom već postoji.";
		}
		return "Uspešno ste uneli novog korisnika.";
	}

	private boolean checkStand(String standNumber) {
		try {
			int standNum = Integer.parseInt(standNumber);
			if (standNum < 1 || standNum > Constants.NUMBER_OF_STANDS)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public FacultyService getFacultyService() {
		return facultyService;
	}
	public void setFacultyService(FacultyService facultyService) {
		this.facultyService = facultyService;
	}
	public PropertyService getPropertyService() {
		return propertyService;
	}
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
}
