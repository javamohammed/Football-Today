package com.mido.football.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.dao.repository.MatchRepository;
import com.mido.football.entity.Match;
import com.mido.football.entity.Player;

@Service
@Transactional("transactionManagerJpa")
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;
	
	public void save(Match match) {
		matchRepository.save(match);
		
	}
	
	public boolean leagueAlready(int leagueId) {
		List<Match> matchs  = matchRepository.findByLeague_id(leagueId);
		
		if(matchs.size() == 0) {
				return true;
		}
		return false;
	}
	
	public List<Match> findByLeague_id(int leagueId) {
		return matchRepository.findByLeague_id(leagueId);
	}
	
	public Match findById(int id) {
		Match match = null;
		Optional<Match> result =  matchRepository.findById(id);
		if(result != null) {
			match = result.get();
		}
		return match;
	}
	public List<Match> scheduleMatchs() {
		
		List<Match> matchs = matchRepository.findAll();
		List<Match> schedule = new ArrayList<Match>();
		for (Match match : matchs) {
			if (match.getResult().equalsIgnoreCase("-")) {
				schedule.add(match);
			}
		}
		return schedule;
		
	}
}
