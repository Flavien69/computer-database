package com.flavien.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.flavien.models.Computer;

public interface ComputerRepository extends PagingAndSortingRepository<Computer, Integer> {

	/**
	 * Get a list of Computer from the database.
	 * 
	 * @return List<Computer>
	 */
	@Transactional
	public List<Computer> findAll();
	
	/**
	 * Get a page of computers.
	 * 
	 * @param name
	 * @param companyName
	 * @param pageable
	 */
	@Transactional
	public Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable pageable);

	/**
	 * Delete all the computers where the company match in the database. Need to
	 * pass the id of the company that's all computers need to be deleted and
	 * the connection uses to the transaction to this function.
	 * 
	 * @param companyId
	 */
	@Transactional
	public void deleteByCompanyId(int companyId);

	/**
	 * Get the total number of computers in the database.
	 * 
	 * @param name
	 *            of the computer to match
	 * @return the number of computer find in the database.
	 */
	@Transactional
	@Query(value = "SELECT COUNT(*) AS count FROM computer LEFT JOIN company ON "
			+ "computer.company_id=company.id WHERE computer.name like %:search% or "
			+ "company.name like %:search%",nativeQuery = true)
	public int getCount(@Param("search") String search);

	/**
	 * Get all computers in the database that match the name.
	 * 
	 * @param name
	 *            of the computer to match.
	 * @return all the computers matching the name find in the database.
	 */
	@Transactional
	public List<Computer> findByName(String name);
	
}
