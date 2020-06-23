package com.mido.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.mido.football.validation.LeagueAlreadyGenerated;

@Entity
@Table(name = "matchs")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "week")
	private String week;

	@Column(name = "season")
	private String season;
	
	@Column(name = "day_match")
	private String dayMatch;

	@Column(name = "result")
	private String result;
	
	@OneToOne
	@JoinColumn(name = "league_id")
	@NotNull(message = "please choose a League")
	//@LeagueAlreadyGenerated
	private League league;
	
	@OneToOne
	@JoinColumn(name = "local")
	private Club local;
	
	@OneToOne
	@JoinColumn(name = "visitor")
	private Club visitor;
	
	public Match() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public String getDayMatch() {
		return dayMatch;
	}

	public void setDayMatch(String dayMatch) {
		this.dayMatch = dayMatch;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Club getLocal() {
		return local;
	}

	public void setLocal(Club local) {
		this.local = local;
	}

	public Club getVisitor() {
		return visitor;
	}

	public void setVisitor(Club visitor) {
		this.visitor = visitor;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", week=" + week + ", season=" + season + ", dayMatch=" + dayMatch + ", result="
				+ result + ", league=" + league + ", local=" + local + ", visitor=" + visitor + "]";
	}

	
	
	
}
