package com.pd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LoginLogout")
public class LoginLogoutController {

	@GetMapping("/Login")
	public String logIn() {
		return "login";
	}
	
	@GetMapping("/Logout")
	public String logOut() {
		return "logout";
	}
	
}
