package com.flavien.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Computer {
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private int company_id;
	
	public Computer() {
		super();
	}

	public Computer(int id, String name, LocalDate introduced,
			LocalDate discontinued, int company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	
	public Computer(int id, String name, Timestamp introduced,
			Timestamp discontinued, int company_id) {
		super();
		this.id = id;
		this.name = name;
		try{
			this.introduced = introduced.toLocalDateTime().toLocalDate();
		}
		catch(NullPointerException e ){
			this.introduced = null;
		}
		try{
			this.discontinued = discontinued.toLocalDateTime().toLocalDate();
		}
		catch(NullPointerException e ){
			this.discontinued = null;
		}		

		this.company_id = company_id;
	}
	
	public Computer(LocalDateTime introduced) {
		super();
		try{
			this.introduced = introduced.toLocalDate();
		}
		catch(NullPointerException e ){
			this.introduced = null;
		}
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
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", company_id=" + company_id + "]";
	}
	
}
