package com.pd.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pd.repository.ClanarinaRepository;
import com.pd.repository.KorisnikRepository;
import com.pd.repository.UlogaRepository;
import com.pd.security.GeneratePassword;

import model.Clanarina;
import model.Korisnik;
import model.Uloga;


//@RestController
@Controller
@RequestMapping("/")
public class UlogeController {
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	KorisnikRepository krepo;
	@Autowired
	UlogaRepository urepo;
	@Autowired
	ClanarinaRepository crepo;
	
	@GetMapping("/")
	public String home() {
		return "login";
	}
	
	@GetMapping("/guest")
	public String guest(Principal principal) {
		String username = principal.getName();
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		request.getSession().setAttribute("korisnik", k);
		
		List<Korisnik> korisnici = krepo.findAll();
		List<Korisnik> zahtevi = korisnici.stream()
				.filter(tmp -> tmp.getUloga() == null)
				.collect(Collectors.toList());
		List<Korisnik> clanovi = korisnici.stream()
				.filter(tmp -> tmp.getUloga() != null)
				.filter(tmp -> tmp.getUloga().getTip().equals("Planinar"))
				.collect(Collectors.toList());
		request.getSession().setAttribute("zahtevi", zahtevi);
		request.getSession().setAttribute("clanovi", clanovi);
		
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
	@GetMapping("/admin/uclani")
	public String admin(String username) {
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		Uloga planiar = urepo.findById(2).get();
		
		k.setUloga(planiar);
		Clanarina c = new Clanarina();
		c.setPocetak(new Date());
		c.setKraj(Date.from(LocalDate.now().plusYears(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		crepo.save(c);
		k.setClanarina(c);
		krepo.save(k);
		
		return "index";
	}
	
	/*
	@GetMapping("/planinar/getSights")
	public String getSights() {
		return "sights";
	}*/
	
	@PostMapping("/registration")
	public String registration(String punoime, String username, String password) {
		password = GeneratePassword.encryptPassword(password);
		
		Korisnik k = new Korisnik();
		k.setIme(punoime.split(" ")[0]);
		k.setPrezime(punoime.split(" ")[1]);
		k.setKorisnickoIme(username);
		k.setSifra(password);
		krepo.save(k);
		
		return "redirect:/guest";
	}
}
