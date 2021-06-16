package com.moolah.category;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = CategoryMapperImpl.class)
public class CategoryMapperTest {

	@InjectMocks
	CategoryMapperImpl categoryMapper;
	
	@Test
	public void MappedCategoryDTO_ShouldBeEquals_GivenCategory() {
		
		Category category = new Category();
		category.setId(1L);
		category.setName(ECategory.CATEGORY_INCOME);
		
		CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
		
		assertEquals(category.getId(), categoryDTO.getId());
		assertEquals(category.getName().name(), categoryDTO.getName());
	}
	
	@Test
	public void MappedCategory_ShouldBeEquals_GivenCategoryDTO() {
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setName(ECategory.CATEGORY_INCOME.name());
		
		Category category = categoryMapper.toCategory(categoryDTO);
		
		assertEquals(categoryDTO.getId(), category.getId());
		assertEquals(categoryDTO.getName(), category.getName().name());
	}
	
	@Test 
	public void MappedCategoryDTOList_ShouldBeEquals_GivenCategoryList() {
		
		Category category1 = new Category();
		category1.setId(1L);
		category1.setName(ECategory.CATEGORY_INCOME);
		
		Category category2 = new Category();
		category2.setId(2L);
		category2.setName(ECategory.CATEGORY_EXPENSE);
		
		Category category3 = new Category();
		category3.setId(3L);
		category3.setName(ECategory.CATEGORY_INCOME);
		
		Category category4 = new Category();
		category4.setId(4L);
		category4.setName(ECategory.CATEGORY_EXPENSE);
		
		List<Category> categories = new ArrayList<Category>();
		
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		categories.add(category4);
		
		List<CategoryDTO> categoryDTOs = categoryMapper.toCategoryDTOList(categories);
		
		assertEquals(categories.get(0).getId(), categoryDTOs.get(0).getId());
		assertEquals(categories.get(0).getName().name(), categoryDTOs.get(0).getName());
		
		assertEquals(categories.get(1).getId(), categoryDTOs.get(1).getId());
		assertEquals(categories.get(1).getName().name(), categoryDTOs.get(1).getName());
		
		assertEquals(categories.get(2).getId(), categoryDTOs.get(2).getId());
		assertEquals(categories.get(2).getName().name(), categoryDTOs.get(2).getName());
		
		assertEquals(categories.get(3).getId(), categoryDTOs.get(3).getId());
		assertEquals(categories.get(3).getName().name(), categoryDTOs.get(3).getName());
	}
	
	@Test
	public void MappedCategoryDTO_ShouldBeNull() {
		
		Category category = null;
		
		CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
		
		assertNull(categoryDTO);
	}
	
	@Test
	public void MappedCategory_ShouldBeNull() {
		
		CategoryDTO categoryDTO = null;
		
		Category category = categoryMapper.toCategory(categoryDTO);
		
		assertNull(category);
	}
	
	@Test
	public void MappedCategoryDTOList_ShouldBeNull() {
		
		List<Category> categories = null;
		
		List<CategoryDTO> categoryDTOs = categoryMapper.toCategoryDTOList(categories);
		
		assertNull(categoryDTOs);
	}
}
