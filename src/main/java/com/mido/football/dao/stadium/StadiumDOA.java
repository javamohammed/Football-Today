package com.mido.football.dao.stadium;

import java.util.List;
import com.mido.football.entity.Stadium;

public interface StadiumDOA {

	public void save(Stadium stadium);
	public List<Stadium> getAllStadiums();
	public Stadium getStadium(int id);
	public List<Stadium> getStadiumsByCountry(String nameCountry);
}
