package com.moolah.type;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.moolah.category.CategoryService;
import com.moolah.user.UserService;

@Mapper(componentModel = "spring")
public abstract class TypeMapper {

	@Autowired
	protected CategoryService categoryService;

	@Autowired
	protected UserService userService;
	
	@Mappings({
		@Mapping(target = "user", expression = "java(userService.findUserById(typeDTO.getUser().getId()))")
	})
	public abstract Type toType(TypeDTO typeDTO);
	
	@InheritInverseConfiguration
	public abstract TypeDTO toTypeDTO(Type type);

	public abstract List<Type> toTypeList(List<TypeDTO> typeDTOs);
	
	@InheritInverseConfiguration
	public abstract List<TypeDTO> toTypeDTOList(List<Type> types);
	
	@Mappings({
		@Mapping(target = "id", expression = "java(null)"),
		@Mapping(target = "category", expression = "java(categoryService.findCategoryById(createTypeDTO.getCategoryId()))"),
		@Mapping(target = "user", expression = "java(userService.findUserById(createTypeDTO.getUserId()))")
	})
	public abstract Type toNewType(CreateTypeDTO createTypeDTO);
}
