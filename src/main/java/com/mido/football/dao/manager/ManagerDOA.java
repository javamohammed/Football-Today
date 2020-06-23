package com.mido.football.dao.manager;

import java.util.List;

import com.mido.football.entity.Manager;

public interface ManagerDOA {

	public void save(Manager manager);
	public List<Manager> getAllManagers();
	public List<Manager> getNotActiveManagers();
	public Manager getManager(int id);
}
