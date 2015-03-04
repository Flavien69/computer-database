package com.flavien.dto.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.flavien.dto.ComputerDTO;
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
	private MessageSource messageSource;

	public ComputerDTO toDto(Computer computer) {
		DateTimeFormatter localeFormatter = DateTimeFormatter.ofPattern(getDatePattern());

		return new ComputerDTO.Builder()
				.id(computer.getId())
				.name(computer.getName())
				.company(computer.getCompany())
				.introduced(
						computer.getIntroduced() == null ? null : computer.getIntroduced().format(
								localeFormatter))
				.discontinued(
						computer.getDiscontinued() == null ? null : computer.getDiscontinued().format(
								localeFormatter)).build();

	}

	public Computer fromDto(ComputerDTO computerDTO) {
		return new Computer.Builder().id(computerDTO.getId()).name(computerDTO.getName())
				.introduced(Utils.getLocalDateTime(computerDTO.getIntroduced(), getDatePattern()))
				.discontinued(Utils.getLocalDateTime(computerDTO.getDiscontinued(), getDatePattern()))
				.company(computerDTO.getCompany()).build();
	}

	public List<ComputerDTO> listToDto(List<Computer> computers) {
		return computers.stream().map(c -> toDto(c)).collect(Collectors.toList());
	}

	public List<Computer> listFromDto(List<ComputerDTO> computerDTOs) {
		return computerDTOs.stream().map(c -> fromDto(c)).collect(Collectors.toList());
	}

	/**
	 * This method returns the date pattern that is associated with the current
	 * Locale.
	 * 
	 * @return The date pattern of the current locale.
	 */
	private String getDatePattern() {
		Locale userLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("date.format", null, userLocale);
	}
}
