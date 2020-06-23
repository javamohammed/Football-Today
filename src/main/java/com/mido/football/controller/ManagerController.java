package com.mido.football.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mido.football.entity.Club;
import com.mido.football.entity.Manager;
import com.mido.football.entity.Stadium;
import com.mido.football.service.country.CountryService;
import com.mido.football.service.manager.ManagerService;
import com.mido.football.services.ClubService;

@Controller
public class ManagerController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ClubService clubService;
	
	@Value("${managers.home}")
	private String routePath;
	
	@GetMapping("${managers.home}")
	public String listManagers(Model model) {
		List<Manager> managers = this.managerService.getAllManagers();
		model.addAttribute("managers", managers);
		model.addAttribute("label", "Managers");
		return "managers/list-manager";
	}
	
	@GetMapping("${managers.show}")
	public String showManager(@PathVariable int id, Model model) {
		
		Manager manager = managerService.getManager(id);
		Club club = null;
		if(manager.getActive() == 0) {
			club = clubService.findById(5);
		}else {
			club = clubService.findByManager_id(id);
		}
		
		System.out.println(club);
		model.addAttribute("clubs", clubService.findAll());
		model.addAttribute("club", club);
		model.addAttribute("manager", manager);
		model.addAttribute("label", "Managers");
		return "managers/show-manager";
	}
	@PostMapping("${managers.change}")
	public String changeClub(@RequestParam("club_id") String clubId, @PathVariable int id,@PathVariable int oldClubId, Model model) {
		//@ModelAttribute("user") @Valid User user, BindingResult result,Model model
		Club club = clubService.findById(Integer.parseInt(clubId));
		//old Manager Update
		Manager oldManager = club.getManager();
		oldManager.setActive(0);
		managerService.save(oldManager);
		
		Manager  manager = managerService.getManager(id);
		club.setManager(manager);

		if(club.getId() == 5) {	 
			manager.setActive(0);
		}else {
			manager.setActive(1);
		}
		managerService.save(manager);
		this.clubService.save(club);
		//Old Club update
		Club oldClub = clubService.findById(oldClubId);
		Manager free = managerService.getManager(0);
		oldClub.setManager(free);
		clubService.save(oldClub);
		
		System.out.println("--------"+clubId);
		return "redirect:"+routePath;
	}
	
	
	@GetMapping("${managers.new}")
	public String formManager(Model model) {
		
		Manager manager = new Manager();
		model.addAttribute("countries", countryService.getAllCountriesNames());
		model.addAttribute("manager", manager);
		model.addAttribute("label", "Managers");
		return "managers/new-manager";
	}
	
	@PostMapping("${managers.save}")
	public String saveManager(@ModelAttribute("manager") @Valid Manager manager, BindingResult result,Model model) {
		//@ModelAttribute("user") @Valid User user, BindingResult result,Model model	
		
		if (result.hasErrors()) {
			model.addAttribute("manager", manager);
			model.addAttribute("countries", countryService.getAllCountriesNames());
			model.addAttribute("label", "Managers");
			return "managers/new-manager";
	      }
		this.managerService.save(manager);
		System.out.println(manager);
		return "redirect:"+routePath;
	}
}
