package com.mido.football.service.league;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mido.football.dao.league.LeagueDOA;
import com.mido.football.entity.League;

@Service
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueDOA leagueDOA;

	@Override
	public void save(League league) {
		this.leagueDOA.save(league);

	}

	@Override
	public List<League> list() {
		// TODO Auto-generated method stub
		return this.leagueDOA.list();
	}

	@Override
	public List<League> getLeaguesByCountry(String nameCountry) {
		// TODO Auto-generated method stub
		return leagueDOA.getLeaguesByCountry(nameCountry);
	}

}
