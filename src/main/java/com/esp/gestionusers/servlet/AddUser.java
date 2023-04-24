package com.esp.gestionusers.servlet;


import java.net.http.HttpClient.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.esp.gestionusers.beans.Utilisateur;
import com.esp.gestionusers.dao.UserRepository;
import com.esp.gestionusers.dao.UserRepositoryImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class AddUser {
@Autowired
	private UserRepository userRepository;
	
@Autowired
private UserRepositoryImp userRepositoryy;
@Autowired
private PasswordEncoder PasswordEncoder;

	@GetMapping("/user/add")
	public String add() {
		
		
		return "vues/adduser";
	}
	
	
	@PostMapping("/user/add")
	public  String  traitement(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,@RequestParam("login") String login, @RequestParam("password") String password ) {
		
		
		Utilisateur user=new Utilisateur(null,nom, prenom, login,  PasswordEncoder.encode(password));
		
		userRepository.save(user);
		
		return "redirect:/user/lister";
	}
	
	@GetMapping("/user/lister")
	public String lister(Model model){
		
		List<Utilisateur> users=  userRepository.findAll();
		model.addAttribute("users", users);
		return "vues/listeusers";
	}
		@GetMapping("/admin/delete")
		public String deletee(@RequestParam("id") Integer id){
			
			userRepository.deleteById(id);
			 return "redirect:/user/lister";
		}
	
		
		@GetMapping("/admin/update")
		public String get( Integer id, ModelMap model) {
		Utilisateur user=	userRepository.findById(id).get();
		if (user !=null) {
			model.addAttribute("user", user);
			return "vues/update";
		}	
		return "redirect:/user/lister";
			
		}
		
		@PostMapping("/admin/update")
		public  String  modifier(@RequestParam("id") Integer id,@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,@RequestParam("login") String login, @RequestParam("password") String password ) {
			
			 if (userRepository.findById(id).isPresent()) {
				 Utilisateur use=userRepository.findById(id).get();
				 use.setNom(nom);
		            use.setPrenom(prenom);
		            use.setUsername(login);
		            use.setPassword(password);
		            userRepository.save(use);
		            
		           
		            return "redirect:/user/lister";
			}
		

			  
			return "User not found";
		}	
			
}
