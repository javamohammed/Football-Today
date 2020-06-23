package com.mido.football.dao.country;

import java.util.List;

import com.mido.football.entity.City;
import com.mido.football.entity.Country;
import com.mido.football.entity.State;

public interface CountryDOA {

	public void save(Country country);
	public List<Country> getAllCountriesNames();
	public List<Country> getCountriesFiltred(String lettre);
	public Country getCountry(String name);
	public Country getCountryById(int id);
	public City getCityById(int id);
	public Country getStatesByCountry(int id);
	public State getCitiesByState(int id);
	
}
