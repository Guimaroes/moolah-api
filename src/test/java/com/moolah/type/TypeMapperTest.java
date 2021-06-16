package com.moolah.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.moolah.category.Category;
import com.moolah.category.CategoryDTO;
import com.moolah.category.CategoryService;
import com.moolah.category.ECategory;
import com.moolah.user.User;
import com.moolah.user.UserDTO;
import com.moolah.user.UserService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TypeMapperImpl.class)
public class TypeMapperTest {

	@InjectMocks
	TypeMapperImpl typeMapper;
	
	@Mock
	CategoryService categoryService;
	
	@Mock
	UserService userService;
	
	@Test
	public void MappedTypeDTO_ShouldBeEquals_GivenType() {
		
		Category category = new Category();
		category.setId(1L);
		category.setName(ECategory.CATEGORY_INCOME);
		
		User user = new User();
		user.setId(1L);
		user.setUsername("username");
		
		Type type = new Type();
		type.setId(1L);
		type.setName("Type");
		type.setCategory(category);
		type.setUser(user);
		
		TypeDTO typeDTO = typeMapper.toTypeDTO(type);
		
		assertEquals(type.getId(), typeDTO.getId());
		assertEquals(type.getName(), typeDTO.getName());
		assertEquals(type.getCategory().getId(), typeDTO.getCategory().getId());
		assertEquals(type.getCategory().getName().name(), typeDTO.getCategory().getName());
		assertEquals(type.getUser().getId(), typeDTO.getUser().getId());
		assertEquals(type.getUser().getUsername(), typeDTO.getUser().getUsername());
	}

	@Test
	public void MappedType_ShouldBeEquals_GivenTypeDTO() {
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setName(ECategory.CATEGORY_INCOME.name());
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("username");
		
		User user = new User();
		user.setId(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		
		Mockito.when(userService.findUserById(userDTO.getId())).thenReturn(user);
		
		TypeDTO typeDTO = new TypeDTO();
		typeDTO.setId(1L);
		typeDTO.setName("Type");
		typeDTO.setCategory(categoryDTO);
		typeDTO.setUser(userDTO);
		
		Type type = typeMapper.toType(typeDTO);
		
		assertEquals(typeDTO.getId(), type.getId());
		assertEquals(typeDTO.getName(), type.getName());
		assertEquals(typeDTO.getCategory().getId(), type.getCategory().getId());
		assertEquals(typeDTO.getCategory().getName(), type.getCategory().getName().name());
		assertEquals(typeDTO.getUser().getId(), type.getUser().getId());
		assertEquals(typeDTO.getUser().getUsername(), type.getUser().getUsername());
	}

	@Test
	public void MappedNewType_ShouldBeEquals_GivenCreateTypeDTO() {
		
		Category category = new Category();
		category.setId(1L);
		
		Mockito.when(categoryService.findCategoryById(category.getId())).thenReturn(category);
		
		User user = new User();
		user.setId(1L);
		
		Mockito.when(userService.findUserById(user.getId())).thenReturn(user);
		
		CreateTypeDTO createTypeDTO = new CreateTypeDTO();
		createTypeDTO.setName("Type");
		createTypeDTO.setCategoryId(category.getId());
		createTypeDTO.setUserId(user.getId());
		
		Type type = typeMapper.toNewType(createTypeDTO);
		
		assertEquals(createTypeDTO.getName(), type.getName());
		assertEquals(createTypeDTO.getCategoryId(), type.getCategory().getId());
		assertEquals(createTypeDTO.getUserId(), type.getUser().getId());
	}
	
	@Test
	public void MappedTypeDTO_ShouldBeNull() {
		Type type = null;
		
		TypeDTO typeDTO = typeMapper.toTypeDTO(type);
		
		assertNull(typeDTO);
	}
	
	@Test
	public void MappedType_ShouldBeNull() {
		TypeDTO typeDTO = null;
		
		Type type = typeMapper.toType(typeDTO);
		
		assertNull(type);
	}
	
	@Test
	public void MappedNewType_ShouldBeNull() {
		CreateTypeDTO createTypeDTO = null;
		
		Type type = typeMapper.toNewType(createTypeDTO);
		
		assertNull(type);
	}
}
