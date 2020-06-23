package com.mido.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "linesup")
public class LineUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "player_team_1")
	private String playerTeam_1;
	
	@Column(name = "player_team_2")
	private String playerTeam_2;
	
	@Column(name = "player_team_1_goal")
	private String playerTeam_1Goals;
	
	@Column(name = "player_team_2_goal")
	private String playerTeam_2Goals;
	
	@Column(name = "player_team_1_yellow_card")
	private String playerTeam_1YellowCards;
	
	@Column(name = "player_team_2_yellow_card")
	private String playerTeam_2YellowCards;
	
	@Column(name = "player_team_1_red_card")
	private String playerTeam_1RedCards;
	
	@Column(name = "player_team_2_red_card")
	private String playerTeam_2RedCards;
	
	@OneToOne
	@JoinColumn(name = "match_id")
	private Match match;
	
	public LineUp() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPlayerTeam_1() {
		return playerTeam_1;
	}

	public void setPlayerTeam_1(String playerTeam_1) {
		this.playerTeam_1 = playerTeam_1;
	}

	public String getPlayerTeam_2() {
		return playerTeam_2;
	}

	public void setPlayerTeam_2(String playerTeam_2) {
		this.playerTeam_2 = playerTeam_2;
	}

	public String getPlayerTeam_1Goals() {
		return playerTeam_1Goals;
	}

	public void setPlayerTeam_1Goals(String playerTeam_1Goals) {
		this.playerTeam_1Goals = playerTeam_1Goals;
	}

	public String getPlayerTeam_2Goals() {
		return playerTeam_2Goals;
	}

	public void setPlayerTeam_2Goals(String playerTeam_2Goals) {
		this.playerTeam_2Goals = playerTeam_2Goals;
	}

	public String getPlayerTeam_1YellowCards() {
		return playerTeam_1YellowCards;
	}

	public void setPlayerTeam_1YellowCards(String playerTeam_1YellowCards) {
		this.playerTeam_1YellowCards = playerTeam_1YellowCards;
	}

	public String getPlayerTeam_2YellowCards() {
		return playerTeam_2YellowCards;
	}

	public void setPlayerTeam_2YellowCards(String playerTeam_2YellowCards) {
		this.playerTeam_2YellowCards = playerTeam_2YellowCards;
	}

	public String getPlayerTeam_1RedCards() {
		return playerTeam_1RedCards;
	}

	public void setPlayerTeam_1RedCards(String playerTeam_1RedCards) {
		this.playerTeam_1RedCards = playerTeam_1RedCards;
	}

	public String getPlayerTeam_2RedCards() {
		return playerTeam_2RedCards;
	}

	public void setPlayerTeam_2RedCards(String playerTeam_2RedCards) {
		this.playerTeam_2RedCards = playerTeam_2RedCards;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "LineUp [id=" + id + ", day=" + day + ", playerTeam_1=" + playerTeam_1 + ", playerTeam_2=" + playerTeam_2
				+ ", playerTeam_1Goals=" + playerTeam_1Goals + ", playerTeam_2Goals=" + playerTeam_2Goals
				+ ", playerTeam_1YellowCards=" + playerTeam_1YellowCards + ", playerTeam_2YellowCards="
				+ playerTeam_2YellowCards + ", playerTeam_1RedCards=" + playerTeam_1RedCards + ", playerTeam_2RedCards="
				+ playerTeam_2RedCards + ", match=" + match + "]";
	}
	
	
	
	

}
