package com.flavien.service;

import java.util.List;

import com.flavien.models.Computer;
import com.flavien.models.Page;

public interface ComputerService {
	/**
	 * Permit to add a computer in the database.
	 * @param Computer
	 */
	public void add(Computer computer);
	
	/**
	 * Get a list of Computer from the database.
	 * @return a List<Computer> 
	 */
	public List<Computer> getAll();
	
	/**
	 * Get a page of Computer from the database.
	 * @param page that is the actual page.
	 * @param name is the name of the computer
	 * @return a Page that is the page[index].
	 */
	public Page getByPage(Page page, String name);
	
	/**
	 * Delete a computer in the database.
	 * @param computerId, the id of the computer to delete.
	 */
	public void deleteById(int computerId);
		
	/**
	 * Delete few computers in the database.
	 * @param computersId, all the ids of the computers to delete.
	 */
	public void deleteMultipleById(String computersId);
	
	/**
	 * Update a computer in the database.
	 * @param computer that is the computer to update.
	 */
	public void update(Computer computer);
	
	/**
	 * Get a computer by id from the database
	 * @param computerId, the id of the computer to find
	 * @return the computer find in the database or null if not found.
	 */
	public Computer getByID(int computerId);
	
	/**
	 * Get all computers in the database that match the name.
	 * @param name of the computer to match.
	 * @return all the computers matching the name find in the database.
	 */
	public List<Computer> getByName(String name);
}
