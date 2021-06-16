package com.moolah.category;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

	public abstract Category toCategory(CategoryDTO categoryDTO);
	
	@InheritInverseConfiguration
	public abstract CategoryDTO toCategoryDTO(Category category);
	
	public abstract List<CategoryDTO> toCategoryDTOList(List<Category> categories);
}
