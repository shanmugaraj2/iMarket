package com.iopex.imarket.service.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.iopex.imarket.service.entity.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("Select pro.name from Company pro where pro.name = ?1")
	String getCompanyName(String name); 
	
	@Query("Select pro from Company pro where pro.status = ?1")
	List<Company> findByStatus(int status);

	
}
