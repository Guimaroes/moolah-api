package com.moolah.type;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moolah.category.CategoryService;
import com.moolah.user.UserService;

@Service
public class TypeService {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private TypeMapper typesMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	public TypeDTO addType(CreateTypeDTO createTypeDTO) {
		return typesMapper.toTypeDTO(typeRepository.saveAndFlush(typesMapper.toNewType(createTypeDTO)));
	}
	
	public Type findTypeById(Long id) {
		return typeRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Type not found.");
		});
	}
	
	public TypeDTO findTypeDTOById(Long id) {
		return typesMapper.toTypeDTO(findTypeById(id));
	}
	
	public List<Type> findAllTypes() {
		return typeRepository.findAll();
	}
	
	public List<TypeDTO> findAllTypeDTOs() {
		return typesMapper.toTypeDTOList(findAllTypes());
	}
	
	public List<Type> findTypesByUserId(Long userId) {
		return typeRepository.findAllByUserId(userId);
	}
	
	public List<TypeDTO> findTypeDTOsByUserId(Long userId) {
		return typesMapper.toTypeDTOList(findTypesByUserId(userId));
	}
	
	public Type updateType(Type type) {
		return typeRepository.saveAndFlush(type);
	}
	
	public TypeDTO updateType(Long id, UpdateTypeDTO updateTypeDTO) {
		Type type = findTypeById(id);
		
		type.setName(updateTypeDTO.getName());
		type.setCategory(categoryService.findCategoryById(updateTypeDTO.getCategoryId()));
		type.setUser(userService.findUserById(updateTypeDTO.getUserId()));
		
		return typesMapper.toTypeDTO(updateType(type));
	}
	
	public void deleteTypeById(Long id) {
		typeRepository.deleteById(id);
	}
}
