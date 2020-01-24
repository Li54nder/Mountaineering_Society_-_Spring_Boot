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

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pd.repository.ClanarinaRepository;
import com.pd.repository.DomRepository;
import com.pd.repository.KomentariseRepository;
import com.pd.repository.KorisnikRepository;
import com.pd.repository.ObilaziRepository;
import com.pd.repository.PlaninaRepository;
import com.pd.repository.RezerviseRepository;
import com.pd.repository.UlogaRepository;
import com.pd.repository.ZnamenitostRepository;
import com.pd.security.GeneratePassword;

import model.Clanarina;
import model.Dom;
import model.Komentarise;
import model.Korisnik;
import model.Obilazi;
import model.Planina;
import model.Rezervise;
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
	@Autowired
	DomRepository drepo;
	@Autowired
	KomentariseRepository komrepo;
	
	
	
	
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
	public String getSights(String idZ, String username) {
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		Znamenitost z = zrepo.findById(Integer.parseInt(idZ)).get();
		
		//TODO: Da li korisnika slati u sesiju opet???
		
		request.getSession().setAttribute("znamenitost", z);		
		
		return "sights";
	}
//	@GetMapping("/user/posetiZnamenitost") Treba da se prosledjuje USERNAME (javlja sa u indexu i u znamenitosti...)
	@GetMapping("/user/posecuje")
	//JEDNOSTAVNO NE SACUVA NISTA...
	public String posetiZnamenitost(String idZ, String idK) {
		Integer idZnamenitost = Integer.parseInt(idZ);
		Integer idKorisnik = Integer.parseInt(idK);
		
		Znamenitost z = zrepo.findById(idZnamenitost).get();
		Korisnik k = krepo.findById(idKorisnik).get();
		
		
		
		if(z.getZakazujeSe()) {
//			PROVERI...
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
						
//						return "index"; //ne radi
					}
				});
			});
		} else {
			Obilazi o = new Obilazi();
			z.getStaza().getPlanina().getDoms().forEach(d -> {
				d.getRezervises().forEach(r -> {
					if(r.getKorisnik().equals(k)) {
						o.setRezervise(r);
						r.getObilazis().add(o);
						rrepo.save(r);
					}
				});
			});
			o.setZnamenitost(z);
			orepo.save(o);
		}
		
		return "redirect:/guest";
	}
	
	@GetMapping("/user/pretragaPlanana")
	public String pretragaPlanina(String idPlanina, String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date result =  df.parse(date);  
		int id = Integer.parseInt(idPlanina);
		
		Planina p = prepo.findById(id).get();
		request.getSession().setAttribute("planina", p);
		
		request.getSession().setAttribute("datumRezervacije", result);
		//TODO: Dobaviti informaciju koliko korisnik ostaje...
		
		return "reservation";
	}
	
	@GetMapping("/user/rezervisiDom")
	public String rezervisiDom(String idD, String username) {
		//TODO: Dobaviti broj dana koliko ostaje...
		int brojDana = 7;
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		Dom d = drepo.findById(Integer.parseInt(idD)).get();
		
		Date pocetak = (Date) request.getSession().getAttribute("datumRezervacije");
		LocalDate localDate = pocetak.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date kraj = Date.from(localDate.plusDays(brojDana).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		Rezervise r = new Rezervise();
		r.setKorisnik(k);
		r.setDom(d);
		r.setPocetak(pocetak);
		r.setKraj(kraj);
		rrepo.save(r);
		
		//TODO: U domu voditi racuna o brojnom stanu....
		
		return "reservation";
	}
	
	@PostMapping("/user/ostaviKomentar")
	public String ostaviKomentar(String komentar, String idZ, String username) {
		Znamenitost z = zrepo.findById(Integer.parseInt(idZ)).get();
		Korisnik k = krepo.findByKorisnickoIme(username).get();
		
		List<Optional<Obilazi>> listaObilazi = orepo.findByZnamenitost(z);
		List<Obilazi> tmp = k.getRezervises().stream().map(r -> r.getObilazis()).flatMap(List::stream).collect(Collectors.toList());
		
		listaObilazi.stream().filter(o -> tmp.stream().anyMatch(x -> x.equals(o.get())));
		
		if(listaObilazi.size() > 0) {
			Komentarise kom = new Komentarise();
			kom.setRezervise(listaObilazi.get(0).get().getRezervise());
			kom.setZnamenitost(z);
			kom.setKomentar(komentar);
			komrepo.save(kom);
		}
		
		return "redirect:/user/getSights";
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
