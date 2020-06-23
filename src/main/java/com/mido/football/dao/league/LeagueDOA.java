package com.mido.football.dao.league;

import java.util.List;

import com.mido.football.entity.League;

public interface LeagueDOA {

	public void save(League league);
	public List<League> list();
	public List<League> getLeaguesByCountry(String nameCountry);
}
