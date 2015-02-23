package com.flavien.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.flavien.models.Computer;
import com.flavien.utils.Utils;

/**
 * 
 * Convert an Computer object to ComputerDTO object and the opposite.
 * 
 */
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
		return  computers.stream().map(c -> toDto(c)).collect(Collectors.toList());
	}
	
	public static List<Computer> listFromDto(List<ComputerDTO> computerDTOs){
		return  computerDTOs.stream().map(c -> fromDto(c)).collect(Collectors.toList());
	}	
}
