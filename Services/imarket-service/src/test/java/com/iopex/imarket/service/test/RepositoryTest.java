package com.iopex.imarket.service.test;


import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iopex.imarket.service.repository.ProspectsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:application-context.xml"})
@Slf4j
public class ProspectsRepositoryTest {
	
	@Resource
	private ProspectsRepository prospectsRepository;
	
	
	@Test
	public void testIsDuplicateCompany() {
		log.info("Test Company");
		String response = prospectsRepository.getCompanyName("");
		log.info("Response Dadta : "+response);
	}

}
