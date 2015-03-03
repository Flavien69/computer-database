package com.flavien.dto;


import org.hibernate.validator.constraints.NotBlank;

import com.flavien.dto.validators.Date;
import com.flavien.models.Company;

/**
 * 
 * Class that represente a computerDTO object. Light computer object to use in the view.
 * 
 */
public class ComputerDTO {
	private int id;
	@NotBlank(message="{computer_dto.name_not_blank}")
	private String name;
	
	@Date(message="{computer_dto.date_introduced_format}")
	private String introduced;
	
	@Date(message="{computer_dto.date_discontinued_format}")
	private String discontinued;
	private Company company;
	
	private ComputerDTO() {}	
	
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
	
	/**
	 * Builder class for a ComputerDTO object
	 *
	 */
	public static class Builder {
		private ComputerDTO computerDTO;

		public Builder() {
			computerDTO = new ComputerDTO();
		}

		/**
		 * Set the id attribute 
		 * @param id
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder id(int id) {
			computerDTO.setId(id);
			return this;
		}

		/**
		 * Set the name attribute
		 * @param name
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder name(String name) {
			computerDTO.setName(name);
			return this;
		}

		/**
		 * Sets the introduced attribute 
		 *
		 * @param introduced
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder introduced(String introduced) {
			computerDTO.setIntroduced(introduced);
			return this;
		}

		/**
		 * Sets the discontinued attribute 
		 *
		 * @param discontinued        
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder discontinued(String discontinued) {
			computerDTO.setDiscontinued(discontinued);
			return this;
		}

		/**
		 * Sets the company attribute
		 *
		 * @param company           
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder company(Company company) {
			computerDTO.setCompany(company);
			return this;
		}

		/**
		 * Creates an instance of ComputerDTO
		 *
		 * @return An instance of ComputerDTO
		 */
		public ComputerDTO build() {
			return computerDTO;
		}
	}
}
