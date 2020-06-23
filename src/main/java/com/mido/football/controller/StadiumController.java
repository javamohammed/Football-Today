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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.mido.football.entity.Stadium;
import com.mido.football.service.country.CountryService;
import com.mido.football.service.stadium.StadiumService;

@Controller
public class StadiumController {

	@Autowired
	private StadiumService stadiumService;
	
	@Autowired
	private CountryService countryService;
	
	@Value("${stadiums.home}")
	private String routePath;
	
	@GetMapping("${stadiums.home}")
	public String listStadiums(Model model) {
		List<Stadium> stadiums = this.stadiumService.getAllStadiums();
		model.addAttribute("stadiums", stadiums);
		model.addAttribute("label", "Stadiums");
		return "stadiums/list-stadium";
	}
	
	@GetMapping("${stadiums.new}")
	public String formStadium(Model model) {
		
		Stadium stadium = new Stadium();
		model.addAttribute("countries", countryService.getAllCountriesNames());
		model.addAttribute("stadium", stadium);
		model.addAttribute("label", "Stadiums");
		return "stadiums/new-stadium";
	}
	
	@PostMapping("${stadiums.save}")
	public String saveStadium(@ModelAttribute("stadium") @Valid Stadium stadium, BindingResult result,Model model) {
		//@ModelAttribute("user") @Valid User user, BindingResult result,Model model	
		
		if (result.hasErrors()) {
			model.addAttribute("stadium", stadium);
			model.addAttribute("countries", countryService.getAllCountriesNames());
			model.addAttribute("label", "Stadiums");
	         return "stadiums/new-stadium";
	      }
		if(stadium.getCity() != null) {
			stadium.setCountry(countryService.getCountryById(Integer.parseInt(stadium.getCountry())).getName());
			stadium.setCity(countryService.getCityById(Integer.parseInt(stadium.getCity())).getName());
		}
		this.stadiumService.save(stadium);
		System.out.println(stadium);
		return "redirect:"+routePath;
	}
}
