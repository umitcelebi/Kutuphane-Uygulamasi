package com.ucelebi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ucelebi.models.User;
import com.ucelebi.repo.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public ModelAndView singUp(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password) {
		
		User user=new User(username, password,"ROLE_USER");
		userRepository.save(user);
		
		return new ModelAndView("login");
	}
	
}
