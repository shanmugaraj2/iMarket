package com.iopex.imarket.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dickens.core.parser.GenericFileReader;
import com.iopex.imarket.service.Imarket;
import com.iopex.imarket.service.entity.Country;
import com.iopex.imarket.service.entity.Industry;
import com.iopex.imarket.service.entity.Prospects;
import com.iopex.imarket.service.repository.CountryRepository;
import com.iopex.imarket.service.repository.IndustryRepository;
import com.iopex.imarket.service.repository.ProspectsRepository;
import com.iopex.imarket.utils.HeaderList;
import com.iopex.imarket.utils.ProspectsVO;

import lombok.extern.slf4j.Slf4j;

@Service("imarket")
@Slf4j
public class ImarketImpl implements Imarket {

	@Resource
	private ProspectsRepository prospectsRepository;
	
	@Resource
	private IndustryRepository industryRepository;
	
	@Resource
	private CountryRepository countryRepository;

	@Transactional
	public ProspectsVO saveProspects(ProspectsVO prospectsVO) {
		try{
			Prospects prospects = convertVoToEntity(prospectsVO);
			prospects = prospectsRepository.save(prospects);
			prospectsVO = convertEntityToVo(prospects);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return prospectsVO;
	}
	
	@Override
	public Map<String, String> getCountries() {
		List<Country> countries = countryRepository.findAll();
		Map<String,String> countryMap = new LinkedHashMap<String,String>();
		for(Country country :countries){
			countryMap.put(country.getName(), country.getName());
		}
		return countryMap;
	}

	@Override
	public Map<String, String> getIndustries() {
		List<Industry> industries = industryRepository.findAll();
		Map<String,String> industryMap = new LinkedHashMap<String,String>();
		for(Industry industry :industries){
			industryMap.put(industry.getName(), industry.getName());
		}
		return industryMap;
	}
	private Prospects convertVoToEntity(ProspectsVO prospectsVO){
		Prospects prospects = new Prospects(
				prospectsVO.getId(), prospectsVO.getCompanyName(), 
				prospectsVO.getContactPerson1(), prospectsVO.getContactJobTitle1(), 
				prospectsVO.getContactEmail1(), prospectsVO.getContactPhone1(),
				prospectsVO.getContactPerson2(), prospectsVO.getContactJobTitle2(), 
				prospectsVO.getContactEmail2(), prospectsVO.getContactPhone2(),
				prospectsVO.getContactPerson3(), prospectsVO.getContactJobTitle3(), 
				prospectsVO.getContactEmail3(), prospectsVO.getContactPhone3(),
				prospectsVO.getIndustry(), prospectsVO.getEmployeeCount(), 
				prospectsVO.getWebsite(), prospectsVO.getCountry(), 
				prospectsVO.getCompanyEmail(), prospectsVO.getRevenue(), 
				prospectsVO.getLeadSource(),prospectsVO.getIsDeleted());
		
		return prospects;
	}
	
	private ProspectsVO convertEntityToVo(Prospects prospects){
		ProspectsVO prospectsVO = new ProspectsVO(
				prospects.getId(), prospects.getCompanyName(), 
				prospects.getContactPerson1(), prospects.getContactJobTitle1(), 
				prospects.getContactEmail1(), prospects.getContactPhone1(),
				prospects.getContactPerson2(), prospects.getContactJobTitle2(), 
				prospects.getContactEmail2(), prospects.getContactPhone2(),
				prospects.getContactPerson3(), prospects.getContactJobTitle3(), 
				prospects.getContactEmail3(), prospects.getContactPhone3(),
				prospects.getIndustry(), prospects.getEmployeeCount(), 
				prospects.getWebsite(), prospects.getCountry(), 
				prospects.getCompanyEmail(), prospects.getRevenue(), 
				prospects.getLeadSource(),prospects.getIsDeleted());
		
		return prospectsVO;
	}

	@Override
	public Boolean isDuplicateCompany(String companyName) {
		String response = prospectsRepository.getCompanyName(companyName);
		Boolean flag = false;
		if(response == null)
			flag = false;
		else if(response.equalsIgnoreCase(companyName))
			flag = true;
		log.info("resutn loger is "+flag);
		return flag;
	}

	@Transactional
	public String uploadPropspects(String filePath) {
		  String response = null;
		  GenericFileReader reader = null;
	        try {
	            reader = GenericFileReader.getReader(filePath,true);
	            Iterator<List<String>> iterator = reader.getIterator(); 
	            List<String> row = null;
	            Map<String,Integer> headerMap = new HashMap<String,Integer>();
	            int i = 0;
	            if(iterator.hasNext()){
	            	row = iterator.next();
	            	 for(String header : row){
	 	            	headerMap.put(header, i);
	 	            	i = i + 1;
	 	            }
	            }
	            response = validation(headerMap);
	            if(response == null || response.isEmpty()){
	            	List<Prospects> prospectsLst = new ArrayList<Prospects>();
	            	Prospects prospects = null;
	            	while(iterator.hasNext()){                              
		            	row = iterator.next();  
		            	prospects = new Prospects(null, row.get(headerMap.get(HeaderList.COMPANY_NAME.header())),
		            			row.get(headerMap.get(HeaderList.CONTACT_PERSON_1.header())), 
		            			row.get(headerMap.get(HeaderList.CONTACT_JOB_TITLE_1.header())),
		            			row.get(headerMap.get(HeaderList.CONTACT_EMAIL_ADDRESS_1.header())), 
		            			row.get(headerMap.get(HeaderList.CONTACT_PHONE_1.header())), 
		            			row.get(headerMap.get(HeaderList.CONTACT_PERSON_2.header())),
		            			row.get(headerMap.get(HeaderList.CONTACT_JOB_TITLE_2.header())), 
		            			row.get(headerMap.get(HeaderList.CONTACT_EMAIL_ADDRESS_2.header())), 
		            			row.get(headerMap.get(HeaderList.CONTACT_PHONE_2.header())),
		            			row.get(headerMap.get(HeaderList.CONTACT_PERSON_3.header())),
		            			row.get(headerMap.get(HeaderList.CONTACT_JOB_TITLE_3.header())), 
		            			row.get(headerMap.get(HeaderList.CONTACT_EMAIL_ADDRESS_3.header())),
		            			row.get(headerMap.get(HeaderList.CONTACT_PHONE_3.header())),
		            			row.get(headerMap.get(HeaderList.INDUSTRY.header())), 
		            			row.get(headerMap.get(HeaderList.EMPLOYEE_COUNT.header())), 
		            			row.get(headerMap.get(HeaderList.COMPANY_WEBSITE.header())),
		            			row.get(headerMap.get(HeaderList.COUNTRY.header())),
		            			row.get(headerMap.get(HeaderList.COMPANY_EMAIL.header())),
		            			row.get(headerMap.get(HeaderList.REVENUE.header())),
		            			row.get(headerMap.get(HeaderList.LEAD_SOURCE.header())),0);
		            	prospectsLst.add(prospects);
		            	if(prospectsLst.size() == 10){
		            		prospectsRepository.save(prospectsLst);
		            		prospectsLst.clear();
		            	}
	               } 
	               if(!prospectsLst.isEmpty())
	 	             prospectsRepository.save(prospectsLst);
	            }
	             
	        } catch (Exception e) {
	        	response = e.getMessage();
	        	throw new RuntimeException(e);
	        } finally{
	              try {
	                reader.close();
	            } catch (Exception e) {
	            	response = e.getMessage();
	            	throw new RuntimeException(e);
	            }
	        }
			return response; 
	}

	public String validation(Map <String,Integer> headeList){
		StringBuffer msg = new StringBuffer();
		if(!headeList.containsKey(HeaderList.COMPANY_NAME.header()))
			msg.append(HeaderList.COMPANY_NAME.header()).append("<br>"); 
		
		if(!headeList.containsKey(HeaderList.CONTACT_PERSON_1.header()))
			msg.append(HeaderList.CONTACT_PERSON_1.header()).append("<br>"); 
		
		if(!headeList.containsKey(HeaderList.CONTACT_JOB_TITLE_1.header()))
			msg.append(HeaderList.CONTACT_JOB_TITLE_1.header()).append("<br>");  
		
		if(!headeList.containsKey(HeaderList.CONTACT_EMAIL_ADDRESS_1.header()))
			msg.append(HeaderList.CONTACT_EMAIL_ADDRESS_1.header()).append("<br>");  
		
		if(!headeList.containsKey(HeaderList.CONTACT_PHONE_1.header()))
			msg.append(HeaderList.CONTACT_PHONE_1.header()).append("<br>");  
		
		if(!headeList.containsKey(HeaderList.CONTACT_PERSON_2.header()))
			msg.append(HeaderList.CONTACT_PERSON_2.header()).append("<br>");  
		
		if(!headeList.containsKey(HeaderList.CONTACT_JOB_TITLE_2.header()))
			msg.append(HeaderList.CONTACT_JOB_TITLE_2.header()).append("<br>");  
		
		if(!headeList.containsKey(HeaderList.CONTACT_EMAIL_ADDRESS_2.header()))
			msg.append(HeaderList.CONTACT_EMAIL_ADDRESS_2.header()).append("<br>");  
		
		if(msg.length() > 0)
			return "Following Headers are Missing "+ "<br>" + msg.toString();
		else
			return null;
	}
	@Override
	public List<ProspectsVO> retriveProspects(int isDeleted) {
		List<Prospects> prospectsLst = prospectsRepository.findByIsDeleted(isDeleted);;
		List<ProspectsVO> prospectsVOLst = new ArrayList<ProspectsVO>();
		for(Prospects prospects : prospectsLst){
			prospectsVOLst.add(convertEntityToVo(prospects));
		}
		return prospectsVOLst;
	}

	@Override
	public ProspectsVO getProspect(Integer id) {
		Prospects prospects = prospectsRepository.findOne(id);
		ProspectsVO prospectsVO = convertEntityToVo(prospects);
		return prospectsVO;
	}

	@Override
	public void removeProspect(Integer id) {
		//prospectsRepository.removeProspect(1,id);
		log.debug("test");
	}

	
}
