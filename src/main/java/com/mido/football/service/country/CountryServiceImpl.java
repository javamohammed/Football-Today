package com.mido.football.service.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mido.football.dao.country.CountryDOA;
import com.mido.football.entity.City;
import com.mido.football.entity.Country;
import com.mido.football.entity.State;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDOA countryDOA;
	
	@Override
	public void save(Country country) {

		countryDOA.save(country);
	}

	@Override
	public List<Country> getAllCountriesNames() {

		return countryDOA.getAllCountriesNames();
	}

	@Override
	public Country getCountry(String name) {
		// TODO Auto-generated method stub
		return countryDOA.getCountry(name);
	}

	@Override
	public List<Country> getCountriesFiltred(String lettre) {
		// TODO Auto-generated method stub
		return countryDOA.getCountriesFiltred(lettre);
	}

	@Override
	public List<State> getStatesByCountry(int id) {
		// TODO Auto-generated method stub
		Country country = countryDOA.getStatesByCountry(id);
		List<State> states = country.getStates();
		return states;
	}

	@Override
	public List<City> getCitiesByState(int id) {
		State state = countryDOA.getCitiesByState(id);
		List<City> cities = state.getCities();
		return cities;
	}

	@Override
	public Country getCountryById(int id) {
		// TODO Auto-generated method stub
		return this.countryDOA.getCountryById(id);
	}

	@Override
	public City getCityById(int id) {
		// TODO Auto-generated method stub
		return this.countryDOA.getCityById(id);
	}

}
