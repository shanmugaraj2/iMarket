package com.iopex.imarket.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iopex.imarket.service.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
}
