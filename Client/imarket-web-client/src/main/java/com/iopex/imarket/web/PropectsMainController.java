package com.iopex.imarket.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iopex.imarket.service.Imarket;
import com.iopex.imarket.utils.ProspectsVO;


@Controller
public class PropectsMainController {

	
	private final String SAVE_MESSAGE = "Successfully Save";
	private final String FAILURE_MESSAGE = "Failed to Save. Please Retry";
	private final String UPLOAD_MESSAGE = "Successfully Upload";
	private final String FAILURE_TO_UPLOAD_MESSAGE = "Failed to Upload. Please Retry";
	
	@Autowired
	public Imarket imarket;
	
	@RequestMapping(value = "/dashboard",method=RequestMethod.GET)
	public String dashboard(Model model) {
		
		List<ProspectsVO> prospectsVOLst = imarket.retriveProspects(0);
		model.addAttribute("prospectsVOLst",prospectsVOLst);
		return "dashboard";
	}
	
	@RequestMapping(value = "/webform",method=RequestMethod.GET)
	public String webform(Model model, @RequestParam(value = "id", required=false) Integer id) {
		Map<String, String> countryMap =  imarket.getCountries();
		Map<String, String> industryMap =  imarket.getIndustries();
		ProspectsVO prospectsVO = null;
		if(id == null){
			prospectsVO = new ProspectsVO();
		}else{
			prospectsVO = imarket.getProspect(id);
		}
		model.addAttribute("countryMap",countryMap);
		model.addAttribute("industryMap",industryMap);
		model.addAttribute("prospectsVO",prospectsVO);
		return "webform";
	}
	@RequestMapping(value = "/remove",method=RequestMethod.GET)
	public String remove(Model model, @RequestParam(value = "id", required=false) Integer id) {
		
		ProspectsVO prospectsVO = null;
		if(id == null){
			prospectsVO = new ProspectsVO();
		}else{
			prospectsVO = imarket.getProspect(id);
		}
		prospectsVO.setIsDeleted(1);
		prospectsVO = imarket.saveProspects(prospectsVO);
		return "redirect:dashboard";
	}
	@RequestMapping(value = "/savePropects",method=RequestMethod.POST)
	public String savePropects(@ModelAttribute("prospectsVO") ProspectsVO prospectsVO,
			RedirectAttributes redirectAttributes) {
		try{
		prospectsVO = imarket.saveProspects(prospectsVO);
		redirectAttributes.addFlashAttribute("message", SAVE_MESSAGE);
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("message", FAILURE_MESSAGE +"\n"+ e.getMessage());
		}
		return "redirect:dashboard";
	}

	@RequestMapping(value = "/isDuplicateCompany",method=RequestMethod.GET)
	public @ResponseBody Boolean isDuplicateCompany(@RequestParam String companyName) {
		Boolean isDuplicate = imarket.isDuplicateCompany(companyName);
		return isDuplicate;
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile file, 
			RedirectAttributes redirectAttributes) {
		String message = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				String path = serverFile.getAbsolutePath();
				path = path.replaceAll("\\\\","\\\\\\\\");
				String response = imarket.uploadPropspects(path);
				if(response == null || response.isEmpty())
					message = UPLOAD_MESSAGE;
				else
					message = response;
			} catch (Exception e) {
				message = FAILURE_TO_UPLOAD_MESSAGE +"<br>"+ e.getMessage();
			}
		
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:dashboard";
	}

}
