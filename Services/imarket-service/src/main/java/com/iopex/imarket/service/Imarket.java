package com.iopex.imarket.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iopex.imarket.utils.ProspectsVO;

@Service
public interface Imarket {

	public ProspectsVO saveProspects(ProspectsVO prospectsVO);
	public Map<String, String> getCountries();
	public Map<String, String> getIndustries();
	public Boolean isDuplicateCompany(String companyName);
	public String uploadPropspects(String filePath);
	public List<ProspectsVO> retriveProspects(int isDeleted);
	public ProspectsVO getProspect(Integer id);
	public void removeProspect(Integer id);
}
