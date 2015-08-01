package rs.fon.queue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@RequestParam(value="error", required=false) Boolean error, Model model) {
		if (error != null && error == true) {
			model.addAttribute("errorMessage", "Pogrešno uneti korisničko ime i/ili lozinka.");
		}
		return "login";
	} 
	
}
