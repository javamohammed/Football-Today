package com.mido.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.mido.football.validation.CountryExists;

@Entity
@Table(name = "manager")
public class Manager {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private int id; 
	 
	 @Column(name = "name")
	 @NotEmpty(message = "please enter name manager")
	 private String name;
	 
	 @Column(name = "date_of_bir")
	 @NotEmpty(message = "please enter date of birthday manager")
	 private String dateOfBir;
	 
	 @Column(name = "salary")
	 @NotEmpty(message = "please enter salary manager")
	 private String salary;

	 @Column(name = "active")
	 private int active;
	 
	 @Column(name = "nationality")
	 @CountryExists
	 @NotEmpty(message = "please enter nationality manager")
	 private String nationality;
	 
	 public Manager() {
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

	public String getDateOfBir() {
		return dateOfBir;
	}

	public void setDateOfBir(String dateOfBir) {
		this.dateOfBir = dateOfBir;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
    
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", dateOfBir=" + dateOfBir + ", salary=" + salary
				+ ", nationality=" + nationality + "]";
	}
	
	
}
