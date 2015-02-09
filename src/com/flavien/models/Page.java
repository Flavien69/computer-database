package com.flavien.models;

import java.util.ArrayList;

public class Page {
	private ArrayList<Computer> computerList;
	private int index;
	public static int NB_ENTITY_BY_PAGE = 10;
	
	public Page() {}
	
	
	public Page(int index) {
		super();
		this.index = index;
	}


	public Page(ArrayList<Computer> computerList, int index) {
		super();
		this.computerList = computerList;
		this.index = index;
	}
	
	public ArrayList<Computer> getComputerList() {
		return computerList;
	}
	public void setComputerList(ArrayList<Computer> computerList) {
		this.computerList = computerList;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
