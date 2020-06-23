package com.mido.football.dao.stadium;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.entity.Stadium;

@Repository
@Transactional("transactionManagerJpa")
public class StadiumHibernateImpl implements StadiumDOA {

	@PersistenceContext
	//@Qualifier("entityManagerFactory")
	private EntityManager entityManager;
	
	public void save(Stadium stadium) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(stadium);

	}

	public List<Stadium> getAllStadiums() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Stadium> query = currentSession.createQuery("FROM Stadium", Stadium.class);
		return query.getResultList();
		//List<Stadium> stadiums = entityManager.createQuery("FROM Stadium", Stadium.class).getResultList();
	    //return stadiums;
	}

	public Stadium getStadium(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Stadium  stadium = currentSession.get(Stadium.class, id);
		return stadium;
	}

	@Override
	public List<Stadium> getStadiumsByCountry(String nameCountry) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Stadium> query = currentSession.createQuery("FROM Stadium WHERE country='"+nameCountry+"'", Stadium.class);
		return query.getResultList();
	}

}
