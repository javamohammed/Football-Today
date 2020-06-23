package com.mido.football.controller;

import java.util.ArrayList;
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

import com.mido.football.entity.Club;
import com.mido.football.entity.Player;
import com.mido.football.service.country.CountryService;
import com.mido.football.services.PlayerService;
import com.mido.football.validation.Utils;

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private CountryService countryService;

	@Value("${players.home}")
	private String routePath;
	
	@GetMapping("${players.home}")
	public String listPlayers(Model model) {
		List<Player> players =new ArrayList<Player>();
		try {
			players = this.playerService.findAll();
		} catch (Exception e) {
			System.out.println("----------");
		}
		System.out.println(players);
		model.addAttribute("players", players);
		model.addAttribute("label", "Players");
		return "players/list-player";
	
	}
	
	@GetMapping("${players.new}")
	public String formPlayer(Model model) {
		
		Player player = new Player();
		model.addAttribute("countries", countryService.getAllCountriesNames());
		model.addAttribute("player", player);
		model.addAttribute("foots", Utils.foots());
		model.addAttribute("positions", Utils.positions());
		model.addAttribute("label", "Players");
		System.out.println(Utils.positions());
		return "players/new-player";
	}

	@PostMapping("${players.save}")
	public String savePlayer(@ModelAttribute("player") @Valid Player player, BindingResult result,Model model) {
		//@ModelAttribute("user") @Valid User user, BindingResult result,Model model	
		
		if (result.hasErrors()) {
			model.addAttribute("player", player);
			model.addAttribute("countries", countryService.getAllCountriesNames());
			model.addAttribute("foots", Utils.foots());
			model.addAttribute("poitions", Utils.positions());
			model.addAttribute("label", "Players");
			return "players/new-player";
	      }
		Club club = new Club();
		club.setId(5);
		player.setClub(club);
		//player.setNationality(countryService.getCountryById(Integer.parseInt(player.getNationality())).getName());
		playerService.save(player);
		System.out.println(player);
		return "redirect:"+routePath;
	}
}
