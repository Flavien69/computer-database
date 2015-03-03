package com.flavien.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flavien.dao.ComputerDao;
import com.flavien.dto.PageDTO;
import com.flavien.dto.PageMapperDTO;
import com.flavien.exception.PersistenceException;
import com.flavien.exception.ServiceException;
import com.flavien.models.Computer;
import com.flavien.models.Page;
import com.flavien.service.ComputerService;

/**
 * 
 * Class that implement the computer service API.
 * 
 */
@Service
public class ComputerServiceImpl implements ComputerService {
  @Autowired
  private ComputerDao computerDao;
  @Autowired
  private PageMapperDTO pageMapperDTO;

  public ComputerServiceImpl() {}

  public ComputerServiceImpl(ComputerDao computerDao) {
    this.computerDao = computerDao;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.flavien.service.ComputerService#add(com.flavien.models.Computer)
   */
  @Override
  public void add(Computer computer) {
    computerDao.add(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.flavien.service.ComputerService#getAll()
   */
  @Override
  public List<Computer> getAll() {
    return computerDao.getAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.flavien.service.ComputerService#getByPage(com.flavien.models.Page,
   * java.lang.String)
   */
  @Override
  @Transactional(readOnly = true, rollbackFor = PersistenceException.class)
  public Page getByPage(Page page) {
    try {
      int count = computerDao.getCount(page.getSearch());
      page = computerDao.getByPage(page);
      page.setNbTotalComputer(count);

    } catch (PersistenceException e) {
      throw new ServiceException(e);
    }

    return page;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.flavien.service.ComputerService#deleteById(int)
   */
  @Override
  public void deleteById(int computerId) {
    computerDao.deleteById(computerId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.flavien.service.ComputerService#update(com.flavien.models.Computer)
   */
  @Override
  public void update(Computer computer) {
    computerDao.update(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.flavien.service.ComputerService#getByID(int)
   */
  @Override
  public Computer getByID(int computerId) {
    return computerDao.getByID(computerId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.flavien.service.ComputerService#getByName(java.lang.String)
   */
  @Override
  public List<Computer> getByName(String name) {
    return computerDao.getByName(name);

  }

}
