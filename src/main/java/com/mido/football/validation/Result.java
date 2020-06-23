package com.mido.football.validation;

public class Result {

	private String name;
	private int points;
	private int goals;
	private int goalsReceived;
	private int diff;
	
	public Result(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = this.points + points;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.diff = this.diff + goals;
		this.goals = this.goals + goals;
	}
	public int getGoalsReceived() {
		return goalsReceived;
	}
	public void setGoalsReceived(int goalsReceived) {
		
		this.diff = this.diff - goalsReceived;
		this.goalsReceived = this.goalsReceived + goalsReceived;
	}
	public int getDiff() {
		return diff;
	}
	@Override
	public String toString() {
		return "Result [name=" + name + ", points=" + points + ", goals=" + goals + ", goalsReceived=" + goalsReceived
				+ ", diff=" + diff + "]";
	}
	
	
}
