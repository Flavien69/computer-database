package com.flavien.dto;

import org.springframework.stereotype.Component;

import com.flavien.models.Page;

@Component 
public class PageMapperDTO {
      
      public PageDTO toDto(Page page){
                PageDTO pageDTO = new PageDTO();
                pageDTO.setIndex(page.getIndex());
                pageDTO.setComputerList(page.getComputerList());
                pageDTO.setNbEntityByPage(page.getNbEntityByPage());
                pageDTO.setNbTotalComputer(page.getNbTotalComputer());
                pageDTO.setNbTotalPage(page.getNbTotalPage());
                pageDTO.setSearch(page.getSearch());
                pageDTO.setSortCriteria(page.getSortCriteria().toString());
                pageDTO.setSortOrder(page.getSortOrder().toString());
                
                return pageDTO;
          
      }
      
      public Page fromDto(PageDTO pageDTO){
          Page page = new Page();
          page.setIndex(pageDTO.getIndex());
          page.setComputerList(pageDTO.getComputerList());
          page.setNbEntityByPage(pageDTO.getNbEntityByPage());
          page.setNbTotalComputer(pageDTO.getNbTotalComputer());
          page.setNbTotalPage(pageDTO.getNbTotalPage());
          page.setSearch(pageDTO.getSearch());
          page.setSortCriteria(Page.SortCriteria.valueOf(pageDTO.getSortCriteria().toUpperCase()));
          page.setSortOrder(Page.SortOrder.valueOf(pageDTO.getSortOrder().toUpperCase()));
          
          return page;     
      }
      
}
