package com.mido.football.dao.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mido.football.entity.Manager;

@Repository
@Transactional("transactionManagerJpa")
public class ManagerHibernateImpl implements ManagerDOA {


	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Manager manager) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(manager);

	}

	@Override
	public List<Manager> getAllManagers() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Manager> query = currentSession.createQuery("FROM Manager", Manager.class);
		return query.getResultList();
	}

	@Override
	public Manager getManager(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Manager  manager= currentSession.get(Manager.class, id);
		return manager;
	}

	@Override
	public List<Manager> getNotActiveManagers() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Manager> query = currentSession.createQuery("FROM Manager WHERE active=0", Manager.class);
		return query.getResultList();
		
	}

}
