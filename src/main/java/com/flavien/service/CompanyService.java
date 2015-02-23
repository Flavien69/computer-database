package com.flavien.service;

import java.util.List;

import com.flavien.models.Company;

/**
 * 
 * API to handle the business layer for the Company object.
 * 
 */
public interface CompanyService {
	/**
     * Get all company present in the database
     * @return List<Company>.
     */
	public List<Company> getAll();
	
	/**
     * Get a company by ID
     * @param companyId
     * @return Company
     */
	public Company getByID(int companyId);
	
	/**
     * Delete a company by ID
     * @param companyId 
     */
	public void deleteByID(int companyId);
}
