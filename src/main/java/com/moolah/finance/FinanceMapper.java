package com.moolah.finance;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.moolah.type.TypeService;
import com.moolah.user.UserService;

@Mapper(componentModel = "spring")
public abstract class FinanceMapper {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected TypeService typeService;
	
	@Mappings({
		@Mapping(target = "user", expression = "java(userService.findUserById(financeDTO.getUser().getId()))"),
		@Mapping(target = "type", expression = "java(typeService.findTypeById(financeDTO.getType().getId()))")
	})
	public abstract Finance toFinance(FinanceDTO financeDTO);
	
	@InheritInverseConfiguration
	public abstract FinanceDTO toFinanceDTO(Finance finance);
	
	public abstract List<Finance> toFinanceList(List<FinanceDTO> financeDTOs);
	
	@InheritInverseConfiguration
	public abstract List<FinanceDTO> toFinanceDTOList(List<Finance> finances);
	
	@Mappings({
		@Mapping(target = "id", expression = "java(null)"),
		@Mapping(target = "type", expression = "java(typeService.findTypeById(createFinanceDTO.getTypeId()))"),
		@Mapping(target = "user", expression = "java(userService.findUserById(createFinanceDTO.getUserId()))")
	})
	public abstract Finance toNewFinance(CreateFinanceDTO createFinanceDTO);
}
