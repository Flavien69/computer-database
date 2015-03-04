package com.flavien.models;

import java.time.LocalDateTime;

/**
 * 
 * Object model that represent a Computer.
 * 
 */
public class Computer {
	private int id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private Company company;

	private Computer() {}

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

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
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
		if (introduced != null)
			str += ", introduced=" + introduced;
		if (discontinued != null)
			str += ", discontinued=" + discontinued;
		if (company.getId() != 0)
			str += ",\t" + company.toString();
		str += "]";

		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Builder class for a Computer object
	 *
	 */
	public static class Builder {
		private Computer computer;

		public Builder() {
			computer = new Computer();
		}

		/**
		 * Set the id attribute 
		 * @param id
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder id(int id) {
			computer.setId(id);
			return this;
		}

		/**
		 * Set the name attribute
		 * @param name
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder name(String name) {
			computer.setName(name);
			return this;
		}

		/**
		 * Sets the introduced attribute 
		 *
		 * @param introduced
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder introduced(LocalDateTime introduced) {
			computer.setIntroduced(introduced);
			return this;
		}

		/**
		 * Sets the discontinued attribute 
		 *
		 * @param discontinued        
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder discontinued(LocalDateTime discontinued) {
			computer.setDiscontinued(discontinued);
			return this;
		}

		/**
		 * Sets the company attribute
		 *
		 * @param company           
		 * @return Builder A reference to the current instance of <i>Builder</i>
		 */
		public Builder company(Company company) {
			computer.setCompany(company);
			return this;
		}

		/**
		 * Creates an instance of Computer
		 *
		 * @return An instance of Computer
		 */
		public Computer build() {
			return computer;
		}
	}

}
