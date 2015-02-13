package com.flavien.models;

import java.util.List;

public class Page {
	private List<Computer> computerList;
	private int nbTotalPage;
	private int nbTotalComputer;
	private int index;
	public static final int NB_ENTITY_BY_PAGE = 10;
	
	public Page() {}
		
	public Page(int index) {
		this.index = index;
	}

	public Page(List<Computer> computerList, int index, int nbTotalComputer) {
		this.computerList = computerList;
		this.index = index;
		this.nbTotalComputer = nbTotalComputer;
		this.nbTotalPage = Math.round(nbTotalComputer/NB_ENTITY_BY_PAGE);
	}

	public Page(List<Computer> computerList, int index) {
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

	public int getNbTotalPage() {
		return nbTotalPage;
	}

	public void setNbTotalPage(int nbTotalPage) {
		this.nbTotalPage = nbTotalPage;
	}

	public void setNbTotalComputer(int nbTotalComputer) {
		this.nbTotalComputer = nbTotalComputer;
	}

	public int getNbTotalComputer() {
		return nbTotalComputer;
	}

	
}
