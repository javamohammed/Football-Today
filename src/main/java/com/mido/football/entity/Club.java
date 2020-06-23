package com.mido.football.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "team")
public class Club {
//`id`, `name`, `code`, `country`, `city`, `address`, `foundation_date`, `logo`, `color_1`, `color_2`, `stadium_id`, `league_id`, `manager_id`
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotEmpty(message = "can not be empty")
	private String name;

	@Column(name = "code")
	private String code;
	
	@Column(name = "country")
	@NotNull(message = "please choose a country")
	private String country;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "address")
	@NotEmpty(message = "can not be empty")
	private String address;
	
	@Column(name = "foundation_date")
	@NotEmpty(message = "can not be empty")
	private String foundationDate;
	
	@Column(name = "logo")
	@Pattern(regexp ="[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)?", message="not a valid link")
	private String logo;
	
	@Column(name = "color_1")
	@NotEmpty(message = "can not be empty")
	private String color_1;
	
	@Column(name = "color_2")
	@NotEmpty(message = "can not be empty")
	private String color_2;
	
	@OneToOne
	@JoinColumn(name = "stadium_id")
	@NotNull(message = "please choose a Stadium")
	private Stadium stadium;
	
	@OneToOne
	@JoinColumn(name = "league_id")
	@NotNull(message = "please choose a League")
	private League league;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	
	
	public Club(int id) {
		this.id = id;
	}

	public Club() {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getColor_1() {
		return color_1;
	}

	public void setColor_1(String color_1) {
		this.color_1 = color_1;
	}

	public String getColor_2() {
		return color_2;
	}

	public void setColor_2(String color_2) {
		this.color_2 = color_2;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", code=" + code + ", country=" + country + ", city=" + city
				+ ", address=" + address + ", foundationDate=" + foundationDate + ", logo=" + logo + ", color_1="
				+ color_1 + ", color_2=" + color_2 + ", stadium=" + stadium + ", league=" + league + ", manager="
				+ manager + "]";
	}
	
	
}
