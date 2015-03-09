package com.flavien.dto;

import java.util.List;

import com.flavien.models.Page;

public class PageDTO {

  private List<ComputerDTO> computerList;
  private int               nbTotalPage;
  private int               nbTotalComputer;
  private int               index;
  private String            sortOrder = "ID";
  private String            search;
  private String            sortCriteria;
  @SuppressWarnings("unused")
  private int[]             range;
  private int               nbEntityByPage = 10;

  public PageDTO() {}

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

  public String getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }

  public String getSortCriteria() {
    return sortCriteria;
  }

  public void setSortCriteria(String sortCriteria) {
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
    int ofset = (int) Page.MAX_PAGE / 2;

    if (nbTotalPage < Page.MAX_PAGE) {
      return new int[] { 0, nbTotalPage };
    }
    if (index < ofset) {
      return new int[] { 0, Page.MAX_PAGE };
    }
    if (index > nbTotalPage - ofset) {
      return new int[] { nbTotalPage - Page.MAX_PAGE, nbTotalPage };
    } else {
      return new int[] { index - ofset, index + ofset };
    }
  }

}
