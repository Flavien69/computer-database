package com.flavien.service.impl;

import java.util.List;

import com.flavien.dao.impl.ComputerDaoImpl;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.ComputerService;

public enum ComputerServiceImpl implements ComputerService{
	INSTANCE;
	private ComputerDaoImpl computerDao = ComputerDaoImpl.INSTANCE;
	
	@Override
	public boolean add(Computer computer) {
		return computerDao.add(computer);
	}

	@Override
	public List<Computer> getAll() {
		return computerDao.getAll();
	}

	@Override
	public Page getByPage(int index) {
		int count = computerDao.getCount();
		if (count > 0){
			List<Computer> computerList = computerDao.getByPage(index);		
			return new Page(computerList, index,count);
		}
		return null;
	}

	@Override
	public boolean deleteById(int computerId) {
		return computerDao.deleteById(computerId);
	}

	@Override
	public boolean update(Computer computer) {
		return computerDao.update(computer);
	}

	@Override
	public Computer getByID(int computerId) {
		return computerDao.getByID(computerId);
	}

}
