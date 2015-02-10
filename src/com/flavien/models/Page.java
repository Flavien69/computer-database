package com.flavien.models;

import java.util.List;

public class Page {
	private List<Computer> computerList;
	private int index;
	public static final int NB_ENTITY_BY_PAGE = 100;
	
	public Page() {}
	
	
	public Page(int index) {
		super();
		this.index = index;
	}


	public Page(List<Computer> computerList, int index) {
		super();
		this.computerList = computerList;
		this.index = index;
	}
	
	public List<Computer> getComputerList() {
		return computerList;
	}
	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
