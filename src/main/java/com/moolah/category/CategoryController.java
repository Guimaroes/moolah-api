package com.moolah.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<CategoryDTO> list() {
		return categoryService.findAllCategoryDTOs();
	}
	
	@GetMapping("/{id}")
	public CategoryDTO getCategory(@PathVariable Long id) {
		return categoryService.findCategoryDTOById(id);
	}
}
