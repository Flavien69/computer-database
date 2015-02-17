package com.flavien.dto;

import java.util.ArrayList;
import java.util.List;

import com.flavien.models.Computer;
import com.flavien.utils.Utils;

public class ComputerMapperDTO {
	
	public static ComputerDTO toDto(Computer computer){
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		computerDTO.setCompany(computer.getCompany());
		
		if(computer.getIntroduced() != null)
			computerDTO.setIntroduced(computer.getIntroduced().toString());
		else
			computerDTO.setIntroduced(null);
		
		if(computer.getDiscontinued() != null)
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		else
			computerDTO.setDiscontinued(null);		
		
		return computerDTO;
	}
	
	public static Computer fromDto(ComputerDTO computerDTO){
		Computer computer = new Computer();
		computer.setId(computerDTO.getId());
		computer.setName(computerDTO.getName());
		computer.setCompany(computerDTO.getCompany());
		computer.setDiscontinued(Utils.getLocalDateTime(computerDTO.getDiscontinued()));
		computer.setIntroduced(Utils.getLocalDateTime(computerDTO.getIntroduced()));
		return computer;
	}
	
	public static List<ComputerDTO> listToDto(List<Computer> computers){
		List<ComputerDTO> computerDTOs = new ArrayList<>();
		for(Computer computer : computers){
			computerDTOs.add(toDto(computer));
		}
		return computerDTOs;
	}
}
