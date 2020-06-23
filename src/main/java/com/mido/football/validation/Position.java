package com.mido.football.validation;

public class Position {

	private String position;
	private String shortCut;
	
	public Position(String shortCut, String position) {
		this.position = position;
		this.shortCut = shortCut;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getShortCut() {
		return shortCut;
	}

	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}
	
}
