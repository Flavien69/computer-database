package com.flavien.service.impl;

import java.util.List;

import com.flavien.dao.ComputerDao;
import com.flavien.dao.impl.DaoManager;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.ComputerService;

/**
 * 
 * Class that implement the computer service API.
 * 
 */
public class ComputerServiceImpl implements ComputerService{
	private ComputerDao computerDao = DaoManager.INSTANCE.getComputerDaoImpl();
	
	public ComputerServiceImpl() {
	}
	
	public ComputerServiceImpl(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
	
	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#add(com.flavien.models.Computer)
	 */
	@Override
	public void add(Computer computer) {
		computerDao.add(computer);
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#getAll()
	 */
	@Override
	public List<Computer> getAll() {
		return computerDao.getAll();
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#getByPage(com.flavien.models.Page, java.lang.String)
	 */
	@Override
	public Page getByPage(Page page, String name) {
		int count = computerDao.getCount(name);
		if (count > 0){
			page = computerDao.getByPage(page, name);
			page.setNbTotalComputer(count);
		}
		return page;
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#deleteById(int)
	 */
	@Override
	public void deleteById(int computerId) {
		computerDao.deleteById(computerId);
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#update(com.flavien.models.Computer)
	 */
	@Override
	public void update(Computer computer) {
		computerDao.update(computer);
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#getByID(int)
	 */
	@Override
	public Computer getByID(int computerId) {
		return computerDao.getByID(computerId);
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#getByName(java.lang.String)
	 */
	@Override
	public List<Computer> getByName(String name){
		return computerDao.getByName(name);
		
	}

	/* (non-Javadoc)
	 * @see com.flavien.service.ComputerService#deleteMultipleById(java.lang.String)
	 */
	@Override
	public void deleteMultipleById(String computersId) {	
		computerDao.deleteMultipleById(computersId);
	}
}
