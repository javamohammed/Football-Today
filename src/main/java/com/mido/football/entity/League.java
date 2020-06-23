package com.mido.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.mido.football.validation.CountryExists;

@Entity
@Table(name = "league")
public class League {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	@NotEmpty(message = "Please enter name league")
	private String name;
	
	@Column(name = "country")
	@CountryExists
	private String country;
	
	@Column(name = "logo")
	@Pattern(regexp ="[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)?", message="not a valid link")
	private String logo;
	
	public League() {
		// TODO Auto-generated constructor stub
	}

	public League(String name, String country, String logo) {
		this.name = name;
		this.country = country;
		this.logo = logo;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", country=" + country + ", logo=" + logo + "]";
	}
	
	
	
}
