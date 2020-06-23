package com.mido.football.service.league;

import java.util.List;

import com.mido.football.entity.League;
public interface LeagueService {

	public void save(League league);
	public List<League> list();
	public List<League> getLeaguesByCountry(String nameCountry);
}
