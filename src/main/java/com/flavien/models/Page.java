package com.flavien.models;

import java.util.List;

import com.flavien.dto.ComputerDTO;

/**
 * 
 * Object model that represent a Page.
 * 
 */
public class Page {

	public static final int DEFAULT_NB_ENTITY_BY_PAGE = 100;
	public static final int MAX_PAGE = 10;

	private List<ComputerDTO> computerList;
	private int nbTotalPage;
	private int nbTotalComputer;
	private int index = 0;
	private SortOrder sortOrder = SortOrder.ASC;
	private String search = "";
	private SortCriteria sortCriteria = SortCriteria.ID;
	@SuppressWarnings("unused")
	private int[] range;
	private int nbEntityByPage = DEFAULT_NB_ENTITY_BY_PAGE;

	public Page() {
	}

	public enum SortCriteria {
		ID, NAME, DISCONTINUED, INTRODUCED, COMPANY_NAME;
	}

	public enum SortOrder {
		DESC, ASC;
	}

	public Page(int index) {
		this.index = index;
	}

	public Page(List<ComputerDTO> computerList, int index, int entityByPage, int nbTotalComputer) {
		this.computerList = computerList;
		this.index = index;
		this.nbEntityByPage = entityByPage;
		this.nbTotalComputer = nbTotalComputer;
		this.nbTotalPage = Math.round(nbTotalComputer / entityByPage);
	}

	public Page(List<ComputerDTO> computerList, int index, int nbTotalComputer) {
		this.computerList = computerList;
		this.index = index;
		this.nbTotalComputer = nbTotalComputer;
		this.nbTotalPage = Math.round(nbTotalComputer / nbEntityByPage);
	}

	public Page(List<ComputerDTO> computerList, int index) {
		this.computerList = computerList;
		this.index = index;
	}

	public List<ComputerDTO> getComputerList() {
		return computerList;
	}

	public void setComputerList(List<ComputerDTO> computerList) {
		this.computerList = computerList;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
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
		this.nbTotalPage = Math.round(nbTotalComputer / nbEntityByPage);
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public SortCriteria getSortCriteria() {
		return sortCriteria;
	}

	public void setSortCriteria(SortCriteria sortCriteria) {
		this.sortCriteria = sortCriteria;
	}

	public int getNbTotalComputer() {
		return nbTotalComputer;
	}

	public int getNbEntityByPage() {
		return nbEntityByPage;
	}

	public void setNbEntityByPage(int entityByPage) {
		this.nbEntityByPage = entityByPage;
	}

	public int[] getRange() {
		int ofset = (int) MAX_PAGE / 2;

		if (nbTotalPage < MAX_PAGE) {
			return new int[] { 0, nbTotalPage };
		}
		if (index < ofset) {
			return new int[] { 0, MAX_PAGE };
		}
		if (index > nbTotalPage - ofset) {
			return new int[] { nbTotalPage - MAX_PAGE, nbTotalPage };
		} else {
			return new int[] { index - ofset, index + ofset };
		}
	}

}
