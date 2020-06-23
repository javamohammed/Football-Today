package com.mido.football.service.country;

import java.util.List;

import com.mido.football.entity.City;
import com.mido.football.entity.Country;
import com.mido.football.entity.State;

public interface CountryService {

	public void save(Country country);
	public List<Country> getAllCountriesNames();
	public List<Country> getCountriesFiltred(String lettre);
	public Country getCountry(String name);
	public List<State> getStatesByCountry(int id);
	public List<City> getCitiesByState(int id);
	public Country getCountryById(int id);
	public City getCityById(int id);
}
