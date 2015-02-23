package com.flavien.models;

/**
 * 
 * Object model that represent a Company.
 * 
 */
public class Company {
	private int id;
	private String name;

	private Company() {}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * Builder class for a Company object
	 *
	 */
	public static class Builder {
		private Company company;

		public Builder() {
			company = new Company();
		}

		/**
		 * Set the id attribute 
		 * @param id
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder id(int id) {
			company.setId(id);
			return this;
		}

		/**
		 * Set the name attribute
		 * @param name
		 * @return Builder A reference to the current instance of Builder
		 */
		public Builder name(String name) {
			company.setName(name);
			return this;
		}
		
		/**
		 * Creates an instance of Company
		 *
		 * @return Company An instance of Company
		 */
		public Company build() {
			return company;
		}
	}
}
