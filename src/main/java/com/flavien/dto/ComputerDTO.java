package com.flavien.dto;


import org.hibernate.validator.constraints.NotBlank;

import com.flavien.dto.validators.Date;
import com.flavien.models.Company;

public class ComputerDTO {
	private int id;
	@NotBlank
	private String name;
	
	@Date
	private String introduced;
	
	@Date
	private String discontinued;
	private Company company;
	
	public ComputerDTO() {}
	
	public ComputerDTO(int id, String name, String introduced,
			String discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public ComputerDTO(int id, String name, String introduced,
			String discontinued, int companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = new Company(companyId);
	}	
	
	public ComputerDTO( String name, String introduced,
			String discontinued, int companyId) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = new Company(companyId);
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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		String str = "Computer [id=" + id + ", name=" + name;
		if(introduced != null)
			str += ", introduced="+ introduced;
		if(discontinued != null)
			str += ", discontinued=" + discontinued;
		if(company.getId() != 0)
			str += ",\t"+company.toString();
		str += "]";
		
		return str;
	}
	
	
}
