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

import com.mido.football.entity.Club;
import com.mido.football.entity.League;
import com.mido.football.entity.Manager;
import com.mido.football.entity.Player;
import com.mido.football.service.country.CountryService;
import com.mido.football.service.league.LeagueService;
import com.mido.football.service.manager.ManagerService;
import com.mido.football.service.stadium.StadiumService;
import com.mido.football.services.ClubService;
import com.mido.football.services.PlayerService;

@Controller
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private StadiumService stadiumService;
	
	@Autowired
	private PlayerService playerService;

	@Value("${clubs.home}")
	private String routePath;

	@GetMapping("${clubs.home}")
	public String listClubs(Model model) {
		List<Club> clubs = this.clubService.findAll();
		model.addAttribute("clubs", clubs);
		model.addAttribute("label", "Clubs");
		return "clubs/list-club";
	}
	
	@GetMapping("${clubs.players}")
	public String listPlayers(@PathVariable int clubId, Model model) {
		List<Player> players = playerService.findByClubId(clubId);
		model.addAttribute("players", players);
		model.addAttribute("players_club", "players_club");
		model.addAttribute("label", "Clubs");
		return "players/list-player";
	}
	
	@GetMapping("${clubs.new}")
	public String formClub(Model model) {
		
		Club club = new Club();
		model.addAttribute("countries", countryService.getAllCountriesNames());
		model.addAttribute("managers", managerService.getNotActiveManagers());
		model.addAttribute("club", club);
		model.addAttribute("label", "Clubs");
		return "clubs/new-club";
	}
	
	@PostMapping("${clubs.save}")
	public String saveClub(@Valid @ModelAttribute("club")  Club club, BindingResult result,Model model) {
		if (result.hasErrors()) {
			model.addAttribute("countries", countryService.getAllCountriesNames());
			model.addAttribute("managers", managerService.getNotActiveManagers());
			model.addAttribute("club", club);
			model.addAttribute("label", "Clubs");
			return "clubs/new-club";
	      }
		club.setCountry(countryService.getCountryById(Integer.parseInt(club.getCountry())).getName());
		if(club.getCity() != null) {
			club.setCity(countryService.getCityById(Integer.parseInt(club.getCity())).getName());
		}
		System.out.println(club);
		clubService.save(club);
		Manager manager = managerService.getManager(club.getManager().getId());
		manager.setActive(1);
		managerService.save(manager);
		return "redirect:"+routePath;
	}
}
