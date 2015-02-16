package com.flavien.models;

import java.util.List;

public class Page {
	
	public static final int DEFAULT_NB_ENTITY_BY_PAGE = 100;
	public static final int MAX_PAGE = 10;

	private List<Computer> computerList;
	private int nbTotalPage;
	private int nbTotalComputer;
	private int index;
	private int[] range;
	private int entityByPage = DEFAULT_NB_ENTITY_BY_PAGE;
	

	public Page() {}
		
	public Page(int index) {
		this.index = index;
	}

	public Page(List<Computer> computerList, int index, int entityByPage, int nbTotalComputer) {
		this.computerList = computerList;
		this.index = index;
		this.entityByPage = entityByPage;
		this.nbTotalComputer = nbTotalComputer;
		this.nbTotalPage = Math.round(nbTotalComputer/entityByPage);
	}
	
	public Page(List<Computer> computerList, int index, int nbTotalComputer) {
		this.computerList = computerList;
		this.index = index;
		this.nbTotalComputer = nbTotalComputer;
		this.nbTotalPage = Math.round(nbTotalComputer/entityByPage);
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

	public int getEntityByPage() {
		return entityByPage;
	}

	public void setEntityByPage(int entityByPage) {
		this.entityByPage = entityByPage;
	}

	public int[] getRange() {
        int ofset = (int) MAX_PAGE/2;

        if(nbTotalPage < MAX_PAGE){
            return new int[] {0,nbTotalPage};
        }
        if(index < ofset  )
        {
            return new int[] {0,MAX_PAGE};
        }
        if(index > nbTotalPage - ofset )
        {
            return new int[] {nbTotalPage - MAX_PAGE,nbTotalPage};
        }
        else
        {
            return new int[] {index -ofset,index +ofset};
        }
    }

}
