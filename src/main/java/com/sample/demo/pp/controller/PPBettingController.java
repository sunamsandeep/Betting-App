package com.sample.demo.pp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.demo.pp.entity.BetDetails;
import com.sample.demo.pp.service.BetFetchingService;
import com.sample.demo.pp.service.ReadFileService;

@Controller
@RequestMapping("/")
public class PPBettingController {
	
	@Autowired
	private BetFetchingService betFetchingService;
	
	@Autowired
	private ReadFileService readFileService;
	
	
	@GetMapping
	public String getBetDetails(Model model){
		model.addAttribute("bettingReport1", betFetchingService.listSelectBettingReport1());
		model.addAttribute("bettingReport2", betFetchingService.listSelectBettingReport2());
		return "view/betdetails";
		}
	
	
	@PostMapping("/fileupload")
	public String uploadFile(@ModelAttribute BetDetails betDetails, RedirectAttributes redirectAttributes) {
		
		boolean isFlag = readFileService.saveData(betDetails.getFile());
		
		if(isFlag) {
			redirectAttributes.addFlashAttribute("successmessage","Upload Successful");
		}else {
			redirectAttributes.addFlashAttribute("errormessage","File not uploaded, Please check the data/format and try again");
		}
		
		return "redirect:/";
		
	}
	
}
