package com.mido.football.dao.country;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.entity.City;
import com.mido.football.entity.Country;
import com.mido.football.entity.State;

@Repository
@Transactional("transactionManagerHibernate")
public class CountryDOAImpl implements CountryDOA {

	@Autowired
	private SessionFactory getSessionFactory;
	
	
	public void save(Country country) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		//currentSession.save(customer);
		currentSession.saveOrUpdate(country);
		
	}

	
	public List<Country> getAllCountriesNames() {
		
		Session currentSession = this.getSessionFactory.getCurrentSession();
		
		Query<Country> query = currentSession.createQuery("from Country", Country.class);
		List<Country> countries = query.getResultList();
		return countries;
	}
	
	public Country getCountry(String name) {
		Country country = null;
		try {
			Session currentSession = this.getSessionFactory.getCurrentSession();
			Query<Country>  query = currentSession.createQuery("from Country WHERE name=?", Country.class);
			query.setParameter(0, name);
			country = query.getSingleResult();
		} catch (Exception e) {
			country = null;
		}
		
		return country;
	}


	public List<Country> getCountriesFiltred(String lettre) {
		
		Session currentSession = this.getSessionFactory.getCurrentSession();
		Query<Country> query = currentSession.createQuery("from Country WHERE name like '"+lettre+"%' ", Country.class);
		List<Country> countries = query.getResultList();
		return countries;
	}


	@Override
	public Country getStatesByCountry(int id) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		Country country =currentSession.get(Country.class, id);
		System.out.println(country);
		return country;
	}


	@Override
	public State getCitiesByState(int id) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		State state =currentSession.get(State.class, id);
		System.out.println(state);
		return state;
	}

	@Override
	public Country getCountryById(int id) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		Country country=currentSession.get(Country.class, id);
		return country;
	}


	@Override
	public City getCityById(int id) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		City city=currentSession.get(City.class, id);
		return city;
	}

}
