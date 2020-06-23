package com.mido.football.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mido.football.entity.Match;
import com.mido.football.services.MatchService;

@Controller
public class HomeController {

	@Autowired
	private MatchService  matchService;
	@GetMapping("/")
	public String getHome(Model model) {
		
		List<Match> matchs = matchService.scheduleMatchs();
		int count = matchs.size() -1 ;
		Random rand = new Random();
		int randomNum = rand.nextInt((count) + 1);
		Match match = matchs.get(randomNum);
		model.addAttribute("match", match);
		model.addAttribute("matchs", matchs);
		return "index";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {

		return "layouts/access-denied";
	}
}
