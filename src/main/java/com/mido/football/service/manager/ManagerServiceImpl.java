package com.mido.football.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mido.football.dao.manager.ManagerDOA;
import com.mido.football.entity.Manager;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDOA managerDOA; 
	@Override
	public void save(Manager manager) {
		managerDOA.save(manager);

	}

	@Override
	public List<Manager> getAllManagers() {
		// TODO Auto-generated method stub
		return managerDOA.getAllManagers();
	}

	@Override
	public Manager getManager(int id) {
		// TODO Auto-generated method stub
		return managerDOA.getManager(id);
	}

	@Override
	public List<Manager> getNotActiveManagers() {
		// TODO Auto-generated method stub
		return managerDOA.getNotActiveManagers();
	}

}
