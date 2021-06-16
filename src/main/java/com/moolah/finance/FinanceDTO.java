package com.moolah.finance;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.moolah.type.TypeDTO;
import com.moolah.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceDTO {

	@NotNull
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private BigDecimal value;
	
	@NotNull
	private TypeDTO type;
	
	@NotNull
	private UserDTO user;
}
