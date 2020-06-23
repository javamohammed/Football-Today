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

import com.mido.football.entity.Club;
import com.mido.football.entity.MarketModel;
import com.mido.football.entity.Player;
import com.mido.football.services.ClubService;
import com.mido.football.services.PlayerService;
import com.mido.football.validation.Utils;



@Controller
public class MarketController {
    
	@Autowired
	private ClubService clubService;

	@Autowired
	private PlayerService playerService;
	
	@Value("${market.home}")
	private String routePath;
	
	@GetMapping("${market.home}")
	public String indexMarket(Model model) {
		MarketModel market = new MarketModel();
		List<Club> clubs = clubService.findAll();
		model.addAttribute("market", market);
		model.addAttribute("clubs", clubs);
		model.addAttribute("label", "Market");
		return "market/index";
	}
	
	@PostMapping("${market.save}")
	public String savePlayer(@ModelAttribute("market") @Valid MarketModel Mmarket, BindingResult result,Model model) {
		//@ModelAttribute("user") @Valid User user, BindingResult result,Model model	
		
		if (result.hasErrors()) {
			MarketModel market = new MarketModel();
			List<Club> clubs = clubService.findAll();
			model.addAttribute("market", market);
			model.addAttribute("clubs", clubs);
			model.addAttribute("label", "Market");
			return "market/index";
	      }

		Player player = playerService.findById(Integer.parseInt(Mmarket.getPlayer()));
		Club club = clubService.findById(Integer.parseInt(Mmarket.getTo()));
		player.setClub(club);
		playerService.save(player);
		return "redirect:"+routePath;
	}
	
}