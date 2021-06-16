package com.moolah.finance;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFinanceDTO {

	@NotBlank
	private String name;
	
	@NotNull
	private BigDecimal value;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long typeId;
}
