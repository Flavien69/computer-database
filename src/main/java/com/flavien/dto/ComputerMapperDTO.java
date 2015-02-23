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
		return  new ComputerDTO.Builder()
			.id(computer.getId())
			.name(computer.getName())
			.company(computer.getCompany())
			.introduced(computer.getIntroduced() == null? null :computer.getIntroduced().toString())
			.discontinued(computer.getDiscontinued() == null? null :computer.getDiscontinued().toString())
			.build();	
		
	}
	
	public static Computer fromDto(ComputerDTO computerDTO){
		return new Computer.Builder()
			.id(computerDTO.getId())
			.name(computerDTO.getName())
			.introduced(Utils.getLocalDateTime(computerDTO.getIntroduced()))
			.discontinued(Utils.getLocalDateTime(computerDTO.getDiscontinued()))
			.company(computerDTO.getCompany())
			.build();		
	}
	
	public static List<ComputerDTO> listToDto(List<Computer> computers){
		return  computers.stream().map(c -> toDto(c)).collect(Collectors.toList());
	}
	
	public static List<Computer> listFromDto(List<ComputerDTO> computerDTOs){
		return  computerDTOs.stream().map(c -> fromDto(c)).collect(Collectors.toList());
	}	
}
