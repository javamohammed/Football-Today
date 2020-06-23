package com.mido.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.mido.football.validation.CountryExists;

@Entity
@Table(name = "player")
public class Player {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private int id; 
	 
	 @Column(name = "name")
	 @NotEmpty(message = "please enter name player")
	 private String name;
	 
	 @Column(name = "position")
	 @NotEmpty(message = "please enter position player")
	 private String position;
	 
	 @Column(name = "favorite_foot")
	 @NotEmpty(message = "please enter favorite foot player")
	 private String favoriteFoot;
	 
	 @Column(name = "jersey_no")
	 @NotEmpty(message = "please enter jersey number  player")
	 private String jerseyNo;
	 
	 @Column(name = "age")
	 @NotEmpty(message = "please enter age player")
	 private String age;
	 
	 @Column(name = "size")
	 @NotEmpty(message = "please enter size player")
	 private String size;
	 
	 @Column(name = "date_of_bir")
	 @NotEmpty(message = "please enter date of birthday player")
	 private String dateOfBir;
	 
	 @Column(name = "value")
	 @NotEmpty(message = "please enter value player")
	 private String value;

	 @Column(name = "nationality")
	 @CountryExists
	 @NotEmpty(message = "please enter nationality player")
	 private String nationality;
	 
	 @OneToOne
	 @JoinColumn(name = "club_id")
	 private Club club;
	 
	 public Player() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFavoriteFoot() {
		return favoriteFoot;
	}

	public void setFavoriteFoot(String favoriteFoot) {
		this.favoriteFoot = favoriteFoot;
	}

	public String getJerseyNo() {
		return jerseyNo;
	}

	public void setJerseyNo(String jerseyNo) {
		this.jerseyNo = jerseyNo;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDateOfBir() {
		return dateOfBir;
	}

	public void setDateOfBir(String dateOfBir) {
		this.dateOfBir = dateOfBir;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", position=" + position + ", favoriteFoot=" + favoriteFoot
				+ ", jerseyNo=" + jerseyNo + ", age=" + age + ", size=" + size + ", dateOfBir=" + dateOfBir + ", value="
				+ value + ", nationality=" + nationality + ", club=" + club + "]";
	}
	
	 
}
