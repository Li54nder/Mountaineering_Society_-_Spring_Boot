package com.pd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Znamenitost;

public interface ZnamenitostRepository extends JpaRepository<Znamenitost, Integer>{
	
}
