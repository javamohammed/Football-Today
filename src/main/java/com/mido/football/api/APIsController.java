package com.mido.football.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mido.football.entity.City;
import com.mido.football.entity.Club;
import com.mido.football.entity.Country;
import com.mido.football.entity.League;
import com.mido.football.entity.Match;
import com.mido.football.entity.Player;
import com.mido.football.entity.Stadium;
import com.mido.football.entity.State;
import com.mido.football.service.country.CountryService;
import com.mido.football.service.league.LeagueService;
import com.mido.football.service.stadium.StadiumService;
import com.mido.football.services.ClubService;
import com.mido.football.services.MatchService;
import com.mido.football.services.PlayerService;

@RestController
public class APIsController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StadiumService stadiumService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping("${api.states}")
	public List<State> getStates(@PathVariable int country){
		return countryService.getStatesByCountry(country);
	}
	
	@GetMapping("${api.cities}")
	public List<City> getCities(@PathVariable int state){
		return countryService.getCitiesByState(state);
	}
	
	@GetMapping("${api.stadiums}")
	public List<Stadium> getStadiums(@PathVariable int country){
		Country count = countryService.getCountryById(country);
		return stadiumService.getStadiumsByCountry(count.getName());
	}
	
	@GetMapping("${api.leagues}")
	public List<League> getLeagues(@PathVariable int country){
		Country count = countryService.getCountryById(country);
		return leagueService.getLeaguesByCountry(count.getName());
	}
	
	@GetMapping("${api.matches}")
	public List<Match> getMatches(@PathVariable int leagueId){
		return matchService.findByLeague_id(leagueId);
	}
	
	@GetMapping("${api.players}")
	public List<Player> getPlayers(@PathVariable int clubId){

		return playerService.findByClubId(clubId);
	}
	
	@GetMapping("${api.clubs}")
	public List<Club> getClubs(){

		return clubService.findAll();
	}
	
	@PostMapping("${api.update}")
	public String updateCapacity(@RequestBody Stadium stadium){
		Stadium oldStadium = stadiumService.getStadium(stadium.getId());
		if(isNumeric(stadium.getCapacity())  ) {
			if(stadium.getCapacity() != "0") {
				oldStadium.setCapacity(stadium.getCapacity());
			}
		}
		stadiumService.save(oldStadium);
		return "Done";
	}
	
	private boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
