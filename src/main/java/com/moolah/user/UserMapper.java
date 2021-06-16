package com.moolah.user;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	@Autowired
	protected UserService userService;
	
	@Mappings({
		@Mapping(target = "password", expression = "java(userService.findUserById(user.getId()).getPassword())"),
		@Mapping(target = "roles", expression = "java(userService.findUserById(user.getId()).getRoles())")
	})
	public abstract User toUser(UserDTO userDTO);
	
	@InheritInverseConfiguration
	public abstract UserDTO toUserDTO(User user);
}
