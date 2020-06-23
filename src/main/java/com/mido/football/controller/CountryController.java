package com.mido.football.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mido.football.entity.Country;
import com.mido.football.service.country.CountryService;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping("${countries.home}")
	public String listCountries(Model model) {
		
		List<Country> countries = countryService.getAllCountriesNames();
		model.addAttribute("countries", countries);
		model.addAttribute("lettres", this.Lettres());
		model.addAttribute("label", "Countries");
		return "countries/list-countries";
	}
	
	@GetMapping("${countries.filter}")
	public String filterCountries(@PathVariable String letter, Model model) {
		
		List<Country> countries = countryService.getCountriesFiltred(letter);
		model.addAttribute("countries", countries);
		model.addAttribute("lettres", this.Lettres());
		model.addAttribute("label", "Countries");
		return "countries/list-countries";
	}
	
	
	private List<String> Lettres(){
		List<String> lettres = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		return lettres;
		
	}
}
