package com.mido.football.entity;

import javax.validation.constraints.NotEmpty;

public class MarketModel {

	@NotEmpty
	private String from;
	
	@NotEmpty
	private String to;
	
	@NotEmpty
	private String player;
	
	public MarketModel() {
		// TODO Auto-generated constructor stub
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
	
}
