package com.pd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{
	
	public Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);

//	public List<Korisnik> findAllWhereUlogaNull() {
//		
//	}
}
