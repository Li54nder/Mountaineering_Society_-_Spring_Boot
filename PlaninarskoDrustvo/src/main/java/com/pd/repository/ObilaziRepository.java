package com.pd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Obilazi;
import model.Rezervise;
import model.Znamenitost;

public interface ObilaziRepository extends JpaRepository<Obilazi, Integer>{

	public List<Optional<Obilazi>> findByZnamenitost(Znamenitost z);
}
