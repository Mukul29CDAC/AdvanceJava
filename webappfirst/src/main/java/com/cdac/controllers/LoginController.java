package com.cdac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.beans.Users;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public void prepareUser(Model data) {
		Users objUser = new Users();
		data.addAttribute("objUser",objUser);
	}
	
	
	@RequestMapping("/authenticate")
	public ModelAndView authenticateUser(@ModelAttribute("objUser") Users objUser) {
		
		if(objUser.getUserName().equals("Mukul") && objUser.getPassword().equals("Mukul")) {
			return new ModelAndView("welcome","msg","Login Succes");
		}else {
			return new ModelAndView("failure","msg","Login Failed");
		}
			
	}
	
}
