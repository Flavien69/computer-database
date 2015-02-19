package com.flavien.service.impl;

import java.util.List;

import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.dao.impl.DaoManager;
import com.flavien.dto.ComputerMapperDTO;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.ComputerService;

public class ComputerServiceImpl implements ComputerService{
	public ComputerDaoImpl computerDao = DaoManager.INSTANCE.getComputerDaoImpl();
	
	public ComputerServiceImpl() {
	}
	
	public ComputerServiceImpl(ComputerDaoImpl computerDao) {
		this.computerDao = computerDao;
	}
	
	@Override
	public void add(Computer computer) {
		computerDao.add(computer);
	}

	@Override
	public List<Computer> getAll() {
		return computerDao.getAll();
	}

	@Override
	public Page getByPage(Page page, String name) {
		int count = computerDao.getCount(name);
		if (count > 0){
			page = computerDao.getByPage(page, name);
			page.setNbTotalComputer(count);
		}
		return page;
	}

	@Override
	public void deleteById(int computerId) {
		computerDao.deleteById(computerId);
	}

	@Override
	public void update(Computer computer) {
		computerDao.update(computer);
	}

	@Override
	public Computer getByID(int computerId) {
		return computerDao.getByID(computerId);
	}

	@Override
	public List<Computer> getByName(String name){
		return computerDao.getByName(name);
		
	}

	@Override
	public void deleteMultipleById(String computersId) {	
		computerDao.deleteMultipleById(computersId);
	}
}
