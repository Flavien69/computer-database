package com.flavien.dao;

import java.util.List;

import com.flavien.models.Computer;
import com.flavien.models.Page;

/**
 * 
 * API to handle database requests for computer object.
 *
 */
public interface ComputerDao {

	/**
	 * Permit to add a computer in the database.
	 * 
	 * @param computer
	 */
	public void add(Computer computer);

	/**
	 * Get a list of Computer from the database.
	 * 
	 * @return a List<Computer>
	 */
	public List<Computer> getAll();

	/**
	 * Get a page of Computer from the database.
	 * 
	 * @param page         
	 * @return Page
	 */
	public Page getByPage(Page page, String name);

	/**
	 * Delete a computer in the database by passing the computer id in
	 * parameters.
	 * 
	 * @param computerId
	 */
	public void deleteById(int computerId);

	/**
	 * Delete all the computers where the company match in the database. Need to
	 * pass the id of the company that's all computers need to be deleted and
	 * the connection uses to the transaction to this function.
	 * 
	 * @param companyId
	 */
	public void deleteByCompanyId(int companyId);


	/**
	 * Update a computer in the database.
	 * 
	 * @param computer
	 *            that is the computer to update.
	 */
	public void update(Computer computer);

	/**
	 * Get a computer by id from the database
	 * 
	 * @param computerId
	 *            , the id of the computer to find
	 * @return the computer find in the database or null if not found.
	 */
	public Computer getByID(int computerId);

	/**
	 * Get the total number of computers in the database.
	 * 
	 * @param name
	 *            of the computer to match
	 * @return the number of computer find in the database.
	 */
	public int getCount(String name);

	/**
	 * Get all computers in the database that match the name.
	 * 
	 * @param name
	 *            of the computer to match.
	 * @return all the computers matching the name find in the database.
	 */
	public List<Computer> getByName(String name);
}
