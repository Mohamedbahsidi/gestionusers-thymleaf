package com.esp.gestionusers.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	
	

	@GetMapping("/notAuthorized")
	public String notAuthorized() {
			return "vues/notAuthorized.html";
			
			
		}
	
	@GetMapping("/login")
	public String login() {
		
		
		return "vues/login";
	}
}
