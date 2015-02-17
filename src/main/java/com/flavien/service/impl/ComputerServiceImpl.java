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
	public boolean add(Computer computer) {
		return computerDao.add(computer);
	}

	@Override
	public List<Computer> getAll() {
		return computerDao.getAll();
	}

	@Override
	public Page getByPage(int index, int nbEntityByPage, String name) {
		int count = computerDao.getCount(name);
		if (count > 0){
			List<Computer> computerList = computerDao.getByPage(index, nbEntityByPage, name);
			return new Page(ComputerMapperDTO.listToDto(computerList), index,nbEntityByPage,count);
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

	@Override
	public List<Computer> getByName(String name){
		return computerDao.getByName(name);
		
	}

	@Override
	public boolean deleteMultipleById(String computersId) {	
		return computerDao.deleteMultipleById(computersId);
	}
}
