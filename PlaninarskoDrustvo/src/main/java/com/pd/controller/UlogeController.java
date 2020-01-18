package com.pd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Dodati pocetnu koja ce da vodi na login... ili nesto neki bin koji ce da dobavi login formu...

@Controller
@RequestMapping("/Uloge")
public class UlogeController {
	
	@GetMapping("/getLogin")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/index/getIndex")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/admin/metoda")
	public String metoda() {
		return "stranica sa necim u sesiji";
	}
	
	@GetMapping("/planinar/getRezervacije")
	public String getRezervacije() {
		return "reservation";
	}
	
	@GetMapping("/planinar/getReport")
	public String getReport() {
		return "report";
	}
	
	@GetMapping("/planinar/getSights")
	public String getSights() {
		return "sights";
	}
}
