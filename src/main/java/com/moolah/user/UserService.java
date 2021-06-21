package com.moolah.user;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO findUserDTOById(Long id) {
		return userMapper.toUserDTO(findUserById(id));
	}
	
	public User findUserById(Long id) {
		return userRepository.findById(id).<EntityNotFoundException>orElseThrow(() -> new EntityNotFoundException("User not found."));
	}
}
