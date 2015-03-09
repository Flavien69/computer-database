package com.flavien.dao.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.flavien.models.Page;

@Component
public class PageRequestMapper {

	public PageRequest toPageRequest(Page page) {
		
		Direction direction = null;
		
		//Get a direction object
		if (page.getSortOrder().toString().equals("ASC"))
			direction = Direction.ASC;
		else
			direction = Direction.DESC;
		
		PageRequest pageRequest =  new PageRequest(page.getIndex(), page.getNbEntityByPage(), direction, page
				.getSortCriteria().toString().toLowerCase());
		return pageRequest;

	}

}
