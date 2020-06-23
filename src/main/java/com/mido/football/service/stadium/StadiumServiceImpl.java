package com.mido.football.service.stadium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mido.football.dao.stadium.StadiumDOA;
import com.mido.football.entity.Stadium;

@Service
public class StadiumServiceImpl implements StadiumService {

	@Autowired
	private StadiumDOA stadiumDOA;
	
	
	public void save(Stadium stadium) {
		this.stadiumDOA.save(stadium);

	}


	public List<Stadium> getAllStadiums() {
		return this.stadiumDOA.getAllStadiums();
	}

	public Stadium getStadium(int id) {

		return this.stadiumDOA.getStadium(id);
	}


	@Override
	public List<Stadium> getStadiumsByCountry(String nameCountry) {
		// TODO Auto-generated method stub
		return this.stadiumDOA.getStadiumsByCountry(nameCountry);
	}

}
