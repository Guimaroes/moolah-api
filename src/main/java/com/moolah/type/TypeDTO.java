package com.moolah.type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.moolah.category.CategoryDTO;
import com.moolah.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDTO {

	@NotNull
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private CategoryDTO category;
	
	@NotNull
	private UserDTO user;
}
