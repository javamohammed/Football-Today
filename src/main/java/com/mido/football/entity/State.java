package com.mido.football.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "country_id")
	private String countryId;
	
	@Column(name = "iso2")
	private String iso2;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "fips_code")
	private String fipsCode;
	
	@Column(name = "updated_at")
	private String updatedAt;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "flag")
	private String flag;
	
	@Column(name = "wikiDataId")
	private String wikiDataId;
	
	@OneToMany(fetch = FetchType.LAZY , cascade =CascadeType.ALL)
	@JoinColumn(name = "state_id")
	public List<City> cities ;
	
	public State() {
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

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getFipsCode() {
		return fipsCode;
	}

	public void setFipsCode(String fipsCode) {
		this.fipsCode = fipsCode;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getWikiDataId() {
		return wikiDataId;
	}

	public void setWikiDataId(String wikiDataId) {
		this.wikiDataId = wikiDataId;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", countryId=" + countryId + ", iso2=" + iso2 + ", countryCode="
				+ countryCode + ", fipsCode=" + fipsCode + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt
				+ ", flag=" + flag + ", wikiDataId=" + wikiDataId  + ", city=" + cities.toString() + "]";
	}	
	
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public void add(City city) {
		if(cities == null) {
			this.cities= new ArrayList<City>();
		}
		this.cities.add(city);
		
	}

	public List<City> getCities() {
		return cities;
	}
	
}
