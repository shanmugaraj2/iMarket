package com.iopex.imarket.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iopex.imarket.service.entity.Industry;

public interface IndustryRepository extends JpaRepository<Industry, Integer> {
	
}
