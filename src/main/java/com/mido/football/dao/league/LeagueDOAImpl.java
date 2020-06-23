package com.mido.football.dao.league;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.entity.League;

@Repository
@Transactional("transactionManagerHibernate")
public class LeagueDOAImpl implements LeagueDOA {

	@Autowired
	private SessionFactory getSessionFactory;
	
	public void save(League league) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		//currentSession.save(customer);
		currentSession.saveOrUpdate(league);
	}

	public List<League> list() {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		
		Query<League> query = currentSession.createQuery("FROM League", League.class);
		return query.getResultList();
	}

	@Override
	public List<League> getLeaguesByCountry(String nameCountry) {
		Session currentSession = this.getSessionFactory.getCurrentSession();
		
		Query<League> query = currentSession.createQuery("FROM League WHERE country='"+nameCountry+"'", League.class);
		return query.getResultList();
	}

}
