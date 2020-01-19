package com.pd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//@RestController
@Controller
@RequestMapping("/")
public class UlogeController {
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/")
	public String home() {
		return "login";
	}
	@GetMapping("/guest")
	public String guest() {
		String username = (String) request.getSession().getAttribute("username");
		
		System.err.println(username);
		
		return "index";
	}
	@GetMapping("/user/getRezervacije")
	public String getRezervacije() {
		return "reservation";
	}
	@GetMapping("user/getReport")
	public String getReport() {
		return "report";
	}
	@GetMapping("/admin")
	public String admin() {
		return "index";
	}
	
	/*
	@GetMapping("/planinar/getSights")
	public String getSights() {
		return "sights";
	}*/
}
