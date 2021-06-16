package com.moolah.type;

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
public class UpdateTypeDTO {

	@NotBlank
	private String name;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long categoryId;
}
