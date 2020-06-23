package com.mido.football.service.stadium;

import java.util.List;

import com.mido.football.entity.Country;
import com.mido.football.entity.Stadium;

public interface StadiumService {

	public void save(Stadium stadium);
	public List<Stadium> getAllStadiums();
	public Stadium getStadium(int id);
	public List<Stadium> getStadiumsByCountry(String nameCountry);
}
