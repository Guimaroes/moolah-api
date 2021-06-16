package com.moolah.category;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}
	
	public List<CategoryDTO> findAllCategoryDTOs() {
		return categoryMapper.toCategoryDTOList(findAllCategories());
	}
	
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Category not found.");
		});
	}

	public CategoryDTO findCategoryDTOById(Long id) {
		return categoryMapper.toCategoryDTO(findCategoryById(id));
	}
}
