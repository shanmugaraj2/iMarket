package com.iopex.imarket.service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.iopex.imarket.service.entity.Prospects;

public interface ProspectsRepository extends JpaRepository<Prospects, Integer> {

	@Query("Select pro.companyName from Prospects pro where pro.companyName = ?1")
	String getCompanyName(String companyName); 
	
	@Query("Select pro from Prospects pro where pro.isDeleted = ?1")
	List<Prospects> findByIsDeleted(int isDeleted);

	
}
