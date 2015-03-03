package com.flavien.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flavien.dto.validators.DateSettings;
import com.flavien.models.Computer;
import com.flavien.utils.Utils;

/**
 * 
 * Convert an Computer object to ComputerDTO object and the opposite.
 * 
 */
@Component
public class ComputerMapperDTO {
	
	@Autowired
	private Utils utils;
	@Autowired
	private DateSettings dateSettings;
	
	public ComputerDTO toDto(Computer computer){
		DateTimeFormatter localeFormatter = DateTimeFormatter
		          .ofPattern(dateSettings.getDatePattern());
		      
		return  new ComputerDTO.Builder()
			.id(computer.getId())
			.name(computer.getName())
			.company(computer.getCompany())
			.introduced(computer.getIntroduced() == null? null :computer.getIntroduced().format(localeFormatter))
			.discontinued(computer.getDiscontinued() == null? null :computer.getDiscontinued().format(localeFormatter))
			.build();	
		
	}
	
	public Computer fromDto(ComputerDTO computerDTO){
		return new Computer.Builder()
			.id(computerDTO.getId())
			.name(computerDTO.getName())
			.introduced(utils.getLocalDateTime(computerDTO.getIntroduced()))
			.discontinued(utils.getLocalDateTime(computerDTO.getDiscontinued()))
			.company(computerDTO.getCompany())
			.build();		
	}
	
	public List<ComputerDTO> listToDto(List<Computer> computers){
		return  computers.stream().map(c -> toDto(c)).collect(Collectors.toList());
	}
	
	public List<Computer> listFromDto(List<ComputerDTO> computerDTOs){
		return  computerDTOs.stream().map(c -> fromDto(c)).collect(Collectors.toList());
	}	
}
