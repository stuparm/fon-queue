package rs.fon.queue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

	@RequestMapping(value="/admin/login", method=RequestMethod.GET)
	public String userLogin(@RequestParam(value="error", required=false) Boolean error, Model model) {
		if (error != null && error == true) {
			model.addAttribute("errorMesage", "Invalid credentials");
		}
		return "admin-login";
	}
	
	
}
