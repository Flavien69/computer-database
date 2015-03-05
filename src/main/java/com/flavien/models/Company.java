package com.flavien.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * Object model that represent a Company.
 * 
 */
@Entity
@Table(name="company")
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;

	@Column(name="name")
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
