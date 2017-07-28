package com.iopex.imarket.service.test;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iopex.imarket.service.entity.Company;
import com.iopex.imarket.service.entity.Contact;
import com.iopex.imarket.service.repository.CompanyRepository;
import com.iopex.imarket.service.repository.ContactRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:application-context.xml"})
@Slf4j
public class RepositoryTest {
	
	@Resource
	private CompanyRepository companyRepository;
	
	@Resource
	private ContactRepository contactRepository;
	
	
	@Test
	public void testIsGetCompanys() {
		log.info("Get Companys");
		List<Company> cmps = companyRepository.findAll();
		
		log.info("Response Dadta : ");
	}
	
	@Test
	public void testIsGetContacts() {
		log.info("Get Contacts");
		List<Contact> contacts = contactRepository.findAll();
		log.info("Response Dadta : ");
	}
	
	@Test
	public void saveData() {
		Set<Contact> contacts = new HashSet<Contact>();
		Company company= new Company(null, "Company1", "Company1", "Company1", "Company1", "Company1",
				"Company1","Company1", "Company1",0,null);
		companyRepository.save(company);
		Contact contact = new Contact(null, "Contact1", "Contact1", "Contact1", "Contact1", "Contact1",company);
		Contact contact1 = new Contact(null, "Contact2", "Contact2", "Contact2", "Contact2", "Contact2",company);
		contacts.add(contact1);
		contacts.add(contact);
		contactRepository.save(contacts);
		
		
	}

	@Test
	public void deleteCompanys() {
		companyRepository.delete(28);
	}
	
	@Test
	public void deleteContact() {
		contactRepository.delete(35);
	}
}
