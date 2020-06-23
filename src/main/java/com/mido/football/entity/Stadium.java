package com.mido.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "stadium")
public class Stadium {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private int id; 
	 
	 @Column(name = "name")
	 @NotEmpty(message = "please enter name of stadium")
	 private String name;
	 
	 @Column(name = "country")
	 private String country;
	 
	 @Column(name = "city")
	 private String city;
	 
	 @Column(name = "capacity")
	 @NotEmpty(message = "please enter capacity of stadium")
	 private String capacity; 
	 
	 @Column(name = "foundation_date")
	 @NotEmpty(message = "please enter fondation date")
	 private String foundationDate;
	 
	 public Stadium() {
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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity= capacity;
	}

	public String getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}

	@Override
	public String toString() {
		return "Stadium [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", capaity="
				+ capacity + ", foundationDate=" + foundationDate + "]";
	}
	
	
	 
	 
}
