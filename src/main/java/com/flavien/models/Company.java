package com.flavien.models;

/**
 * 
 * Object model that represent a Company.
 * 
 */
public class Company {
	private int id;
	private String name;

	public Company() {}

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
