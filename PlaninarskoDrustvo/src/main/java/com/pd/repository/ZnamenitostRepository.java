package com.pd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Znamenitost;

public interface ZnamenitostRepository extends JpaRepository<Znamenitost, Integer>{
	
//	@Query("select z from Znamenitost z where z.idZnamenitost not in (select o.idZnamenitost from Obilazi o where o.idRezervise = (select r.idRezervise from Rezervise r where r.idKorisnik = :id)) and z.idZnamenitost in (select t.idZnamenitost from Termin t where t.idTermin in (select t.idTermin from Termin t where t.idRezervise = (select r.idRezervise from Rezervise r where r.idKorisnik = :id)))")
//	public List<Optional<Znamenitost>> getZakazane(@Param("id") Integer id);
}
