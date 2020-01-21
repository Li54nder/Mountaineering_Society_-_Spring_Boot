package com.pd.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pd.repository.ClanarinaRepository;
import com.pd.repository.KorisnikRepository;
import com.pd.repository.ObilaziRepository;
import com.pd.repository.PlaninaRepository;
import com.pd.repository.RezerviseRepository;
import com.pd.repository.UlogaRepository;
import com.pd.repository.ZnamenitostRepository;
import com.pd.security.GeneratePassword;

import model.Clanarina;
import model.Korisnik;
import model.Obilazi;
import model.Planina;
import model.Uloga;
import model.Znamenitost;


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
	@Autowired
	ZnamenitostRepository zrepo;
	@Autowired
	PlaninaRepository prepo;
	@Autowired
	ObilaziRepository orepo;
	@Autowired
	RezerviseRepository rrepo;
	
	
	
	
//	GUEST
	
	@GetMapping("/guest")
	public String guest(Principal principal) {
		String username = principal.getName();
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		request.getSession().setAttribute("korisnik", k);
		
		//TODO: Proveri da li je korisniku, ako je Clan, istekla clanarina... ako jest izbrisi mi Clanarinu i ULOGU
		
		if(k.getUloga().getTip().equals("Sekretar")) {
			List<Korisnik> korisnici = krepo.findAll();
			List<Korisnik> zahtevi = korisnici.stream()
					.filter(tmp -> tmp.getUloga() == null)
					.collect(Collectors.toList());
			List<Korisnik> clanovi = korisnici.stream()
					.filter(tmp -> tmp.getUloga() != null)
					.filter(tmp -> tmp.getUloga().getTip().equals("Planinar"))
					.collect(Collectors.toList());
			List<Planina> planine = prepo.findAll();
			request.getSession().setAttribute("zahtevi", zahtevi);
			request.getSession().setAttribute("clanovi", clanovi);
			request.getSession().setAttribute("planine", planine);
		}
		
		return "index";
	}
	
	
	
	
//	USER
	
	@GetMapping("/user/getRezervacije")
	public String getRezervacije() {
		List<Planina> planine = prepo.findAll();
		request.getSession().setAttribute("planine", planine);
		
		return "reservation";
	}
	
	@GetMapping("user/getReport")
	public String getReport() {
		return "report";
	}
	
	@GetMapping("/user/getSights")
	public String getSights() {
		return "sights";
	}
	
	@GetMapping("/user/posecuje")
	public String posetiZnamenitost(String idZ, String idK) {
		Integer idZnamenitost = Integer.parseInt(idZ);
		Integer idKorisnik = Integer.parseInt(idK);
		
		Znamenitost z = zrepo.findById(idZnamenitost).get();
		Korisnik k = krepo.findById(idKorisnik).get();
		
		k.getRezervises().forEach(r -> {
			r.getZakazujes().forEach(zakazuje -> {
				if(zakazuje.getTermin().getZnamenitost().equals(z)) {
					zakazuje.getTermin().setZnamenitost(null);
					Obilazi o = new Obilazi();
					o.setRezervise(r);
					o.setZnamenitost(z);
					orepo.save(o);
					
					r.getObilazis().add(o);
					rrepo.save(r);
					
//					return "index"; //ne radi
				}
			});
		});
		
		return "redirect:/guest";
	}
	
	@GetMapping("/user/pretragaPlanana")
	public String pretragaPlanina(String idPlanina, String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date result =  df.parse(date);  
		int id = Integer.parseInt(idPlanina);
		
		Planina p = prepo.findById(id).get();
		request.getSession().setAttribute("planina", p);
		
		//TODO: datum u sesiju... tebace...
		
		return "reservation";
	}
	
	
	
	
//	ADMIN
	
	@GetMapping("/admin/uclani")
	public String uclanjenje(String username) {
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		Uloga planiar = urepo.findById(2).get();
		
		k.setUloga(planiar);
		Clanarina c = new Clanarina();
		c.setPocetak(new Date());
		c.setKraj(Date.from(LocalDate.now().plusYears(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		crepo.save(c);
		k.setClanarina(c);
		krepo.save(k);
		
		return "redirect:/guest";
	}
	
	@GetMapping("/admin/produziClanarinu")
	public String produzenje(String username) {
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		
		Clanarina cl = k.getClanarina();
		Date kraj = cl.getKraj();
		
		LocalDate localDate = kraj.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		kraj = Date.from(localDate.plusYears(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		cl.setKraj(kraj);
		crepo.save(cl);
		
		return "redirect:/guest";
	}
	
	

	
//	SVI

	@GetMapping("/")
	public String home() {
		return "login";
	}
	
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
	
	
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	binder.registerCustomEditor(Date.class, new CustomDateEditor(
//	        dateFormat, false));
//	}
}
