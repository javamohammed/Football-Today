package com.mido.football.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mido.football.dao.repository.ClubRepository;
import com.mido.football.entity.Club;
import com.mido.football.entity.Country;
import com.mido.football.entity.League;
import com.mido.football.entity.Match;
import com.mido.football.entity.Player;
import com.mido.football.service.country.CountryService;
import com.mido.football.service.league.LeagueService;
import com.mido.football.services.ClubService;
import com.mido.football.services.MatchService;


@Controller
public class LeagueController {

	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping("${leagues.home}")
	public String listLeagues(Model model) {
		
		List<League> leagues = leagueService.list();
		model.addAttribute("leagues", leagues);
		model.addAttribute("label", "Leagues");
		return "leagues/list-league";
	}
	
	@GetMapping("${leagues.matches}")
	public String listMatches(@PathVariable int leagueId, Model model) {
		
		List<Match> matches = matchService.findByLeague_id(leagueId);
		model.addAttribute("matches", matches);
		model.addAttribute("matches_league", "matches_league");
		model.addAttribute("leagueId", leagueId);
		model.addAttribute("label", "Leagues");
		return "games/matches";
	}
	
	@GetMapping("${leagues.clubs}")
	public String listClubs(@PathVariable int leagueId, Model model) {
		List<Club> clubs = clubService.findByLeague_id(leagueId);
		model.addAttribute("clubs", clubs);
		model.addAttribute("clubs_league", "clubs_league");
		model.addAttribute("leagueId", leagueId);
		model.addAttribute("label", "Leagues");
		return "clubs/list-club";
	}
	@GetMapping("${leagues.new}")
	public String formLeagues(Model model) {
		
		League league = new League();
		model.addAttribute("league", league);
		model.addAttribute("countries", countryService.getAllCountriesNames());
		model.addAttribute("label", "Leagues");
		return "leagues/new-league";
	}
	
	@PostMapping("${leagues.save}")
	public String saveLeague(@ModelAttribute("league") @Valid League league, BindingResult result,Model model) {
		//@ModelAttribute("user") @Valid User user, BindingResult result,Model model	
		
		if (result.hasErrors()) {
			model.addAttribute("league", league);
			model.addAttribute("countries", countryService.getAllCountriesNames());
			model.addAttribute("label", "Leagues");
	         return "leagues/new-league";
	      }
		this.leagueService.save(league);
		System.out.println(league);
		return "redirect:/leagues";
	}
}
