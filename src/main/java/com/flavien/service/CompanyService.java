package com.flavien.service;

import java.util.List;

import com.flavien.models.Company;

public interface CompanyService {
	/**
     * get all company present in the database
     * @return a list of companies or an empty List if there is no companies.
     */
	public List<Company> getAll();
	
	/**
     * get a company by ID
     * @param companyId the ID of the company needed.
     * @return the company find or null if not found.
     */
	public Company getByID(int companyId);
	
	/**
     * delete a company by ID
     * @param companyId the ID of the company to delete.
     * @return the company find or null if not found.
     */
	public void deleteByID(int companyId);
}
