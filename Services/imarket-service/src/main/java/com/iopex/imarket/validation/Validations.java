package com.iopex.imarket.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.iopex.imarket.utils.HeaderList;

public class Validations {


	public String headerValidation(Map <String,Integer> headeList){
		
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

		if(!headeList.containsKey(HeaderList.CONTACT_PHONE_2.header()))
			msg.append(HeaderList.CONTACT_PHONE_1.header()).append("<br>");  

		if(!headeList.containsKey(HeaderList.CONTACT_PERSON_3.header()))
			msg.append(HeaderList.CONTACT_PERSON_3.header()).append("<br>");  

		if(!headeList.containsKey(HeaderList.CONTACT_JOB_TITLE_3.header()))
			msg.append(HeaderList.CONTACT_JOB_TITLE_3.header()).append("<br>");  

		if(!headeList.containsKey(HeaderList.CONTACT_EMAIL_ADDRESS_3.header()))
			msg.append(HeaderList.CONTACT_EMAIL_ADDRESS_3.header()).append("<br>"); 

		if(!headeList.containsKey(HeaderList.CONTACT_PHONE_3.header()))
			msg.append(HeaderList.CONTACT_PHONE_3.header()).append("<br>");  

		if(!headeList.containsKey(HeaderList.EMPLOYEE_COUNT.header()))
			msg.append(HeaderList.EMPLOYEE_COUNT.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.EMPLOYEE_COUNT.header()))
			msg.append(HeaderList.EMPLOYEE_COUNT.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.COMPANY_WEBSITE.header()))
			msg.append(HeaderList.COMPANY_WEBSITE.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.COUNTRY.header()))
			msg.append(HeaderList.COUNTRY.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.COMPANY_EMAIL.header()))
			msg.append(HeaderList.COMPANY_EMAIL.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.REVENUE.header()))
			msg.append(HeaderList.REVENUE.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.LEAD_SOURCE.header()))
			msg.append(HeaderList.LEAD_SOURCE.header()).append("<br>");


		if(msg.length() > 0)
			return "Following Headers are Missing "+ "<br>" + msg.toString();
		else
			return null;
	}

	public Boolean fieldLevelValidation(List<String> row,Map<String,Integer> headerMap){
		
		StringBuffer errorInfo = new StringBuffer();
		List<String> headerMapList = new ArrayList<String>();
		headerMapList.addAll(headerMap.keySet());
		
		for(int i = 0; i < headerMap.keySet().size();i++){
			if(row.get(headerMap.get(headerMapList.get(i))).isEmpty()){
				errorInfo.append(headerMapList.get(i)).append(" cannot be empty").append("<br>"); 
			}
		}
		/*if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.COMPANY_NAME.header()).append("<br>"); 

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_PERSON_1.header()).append("<br>"); 

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_JOB_TITLE_1.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_EMAIL_ADDRESS_1.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_PHONE_1.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_PERSON_2.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_JOB_TITLE_2.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_EMAIL_ADDRESS_2.header()).append("<br>"); 

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_PHONE_1.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_PERSON_3.header()).append("<br>");  

		if(row.get(headerMap.get(HeaderList.COMPANY_NAME.header())).isEmpty())
			msg.append(HeaderList.CONTACT_JOB_TITLE_3.header()).append("<br>");  

		if(!headeList.containsKey(HeaderList.CONTACT_EMAIL_ADDRESS_3.header()))
			msg.append(HeaderList.CONTACT_EMAIL_ADDRESS_3.header()).append("<br>"); 

		if(!headeList.containsKey(HeaderList.CONTACT_PHONE_3.header()))
			msg.append(HeaderList.CONTACT_PHONE_3.header()).append("<br>");  

		if(!headeList.containsKey(HeaderList.EMPLOYEE_COUNT.header()))
			msg.append(HeaderList.EMPLOYEE_COUNT.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.EMPLOYEE_COUNT.header()))
			msg.append(HeaderList.EMPLOYEE_COUNT.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.COMPANY_WEBSITE.header()))
			msg.append(HeaderList.COMPANY_WEBSITE.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.COUNTRY.header()))
			msg.append(HeaderList.COUNTRY.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.COMPANY_EMAIL.header()))
			msg.append(HeaderList.COMPANY_EMAIL.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.REVENUE.header()))
			msg.append(HeaderList.REVENUE.header()).append("<br>");

		if(!headeList.containsKey(HeaderList.LEAD_SOURCE.header()))
			msg.append(HeaderList.LEAD_SOURCE.header()).append("<br>");*/
		
		return null;
	}

}
