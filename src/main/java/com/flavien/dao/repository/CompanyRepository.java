package com.flavien.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavien.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	/**
	 * get all company present in the database
	 * 
	 * @return a list of companies or an empty List if there is no companies.
	 */
	List<Company> findAll();
}