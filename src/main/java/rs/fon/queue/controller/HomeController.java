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
public class HomeController {
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login(@RequestParam(value="error", required=false) Boolean error, Model model) {
		if (error != null && error == true) {
			model.addAttribute("errorMessage", "Pogresno uneti korisnicko ime i/ili lozinka.");
			System.out.println("DADADA");
		}
		return "login";
	}
	
}
