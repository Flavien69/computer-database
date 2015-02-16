package com.flavien.dao;

import java.util.List;

import com.flavien.models.Computer;

public interface ComputerDao {
	
	/**
	 * Permit to add a computer in the database.
	 * @param Computer
	 * @return true if it is successful or false if it's not.
	 */
	public boolean add(Computer computer);
	
	/**
	 * Get a list of Computer from the database.
	 * @return a List<Computer> 
	 */
	public List<Computer> getAll();
	
	/**
	 * Get a page of Computer from the database.
	 * @param index represent the actual page.
	 * @return a List<Computer> that is the page[index].
	 */
	public List<Computer> getByPage(int index, int nbEntityByPage, String name);
	
	/**
	 * Delete a computer in the database.
	 * @param computerId, the id of the computer to delete.
	 * @return true if the delete is successful or false if it isn't.
	 */
	public boolean deleteById(int computerId);
	
	/**
	 * Delete few computers in the database.
	 * @param computersId, all the ids of the computers to delete.
	 * @return true if the delete is successful or false if it isn't.
	 */
	public boolean deleteMultipleById(String computersId);
	
	/**
	 * Update a computer in the database.
	 * @param computer that is the computer to update.
	 * @return true if the update is successful of false if it isn't.
	 */
	public boolean update(Computer computer);
	
	/**
	 * Get a computer by id from the database
	 * @param computerId, the id of the computer to find
	 * @return the computer find in the database or null if not found.
	 */
	public Computer getByID(int computerId);
	
	/**
	 * Get the total number of computers in the database.
	 * @param name of the computer to match
	 * @return the number of computer find in the database.
	 */
	public int getCount(String name);
	
	/**
	 * Get all computers in the database that match the name.
	 * @param name of the computer to match.
	 * @return all the computers matching the name find in the database.
	 */
	public List<Computer> getByName(String name);
}
