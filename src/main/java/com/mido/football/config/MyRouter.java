package com.mido.football.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyRouter {

	private String route;
	
	@Autowired
	private Environment env;
	
	public MyRouter() {
		// TODO Auto-generated constructor stub
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String nameRoute) {
		this.route = env.getProperty(nameRoute);
	}
	
	
}
