package com.moolah.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

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
import com.moolah.category.ECategory;
import com.moolah.type.Type;
import com.moolah.type.TypeDTO;
import com.moolah.type.TypeService;
import com.moolah.user.User;
import com.moolah.user.UserDTO;
import com.moolah.user.UserService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = FinanceMapperImpl.class)
public class FinanceMapperTest {

	@InjectMocks
	FinanceMapperImpl financeMapper;
	
	@Mock
	TypeService typeService;
	
	@Mock
	UserService userService;
	
	@Test
	public void MappedFinanceDTO_ShouldBeEquals_GivenFinance() {
		
		User user = new User();
		user.setId(1L);
		user.setUsername("User");
		
		Category category = new Category();
		category.setId(1L);
		category.setName(ECategory.CATEGORY_INCOME);
		
		Type type = new Type();
		type.setId(1L);
		type.setName("Type");
		type.setUser(user);
		type.setCategory(category);
		
		Finance finance = new Finance();
		finance.setId(1L);
		finance.setName("Finance");
		finance.setValue(new BigDecimal(100));
		finance.setType(type);
		finance.setUser(user);
		
		FinanceDTO financeDTO = financeMapper.toFinanceDTO(finance);
		
		assertEquals(finance.getId(), financeDTO.getId());
		assertEquals(finance.getName(), financeDTO.getName());
		assertEquals(finance.getValue(), financeDTO.getValue());
		assertEquals(finance.getType().getId(), financeDTO.getType().getId());
		assertEquals(finance.getType().getName(), financeDTO.getType().getName());
		assertEquals(finance.getType().getUser().getId(), financeDTO.getType().getUser().getId());
		assertEquals(finance.getType().getUser().getUsername(), financeDTO.getType().getUser().getUsername());
		assertEquals(finance.getType().getCategory().getId(), financeDTO.getType().getCategory().getId());
		assertEquals(finance.getType().getCategory().getName().name(), financeDTO.getType().getCategory().getName());
		assertEquals(finance.getUser().getId(), financeDTO.getUser().getId());
		assertEquals(finance.getUser().getUsername(), financeDTO.getUser().getUsername());
	}
	
	@Test
	public void MappedFinance_ShouldBeEquals_GivenFinanceDTO() {
		
		User user = new User();
		user.setId(1L);
		user.setUsername("User");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		
		Category category = new Category();
		category.setId(1L);
		category.setName(ECategory.CATEGORY_INCOME);
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName().name());
		
		Type type = new Type();
		type.setId(1L);
		type.setName("Type");
		type.setUser(user);
		type.setCategory(category);
		
		TypeDTO typeDTO = new TypeDTO();
		typeDTO.setId(type.getId());
		typeDTO.setName(type.getName());
		typeDTO.setCategory(categoryDTO);
		typeDTO.setUser(userDTO);
		
		FinanceDTO financeDTO = new FinanceDTO();
		financeDTO.setId(1L);
		financeDTO.setName("Finance");
		financeDTO.setValue(new BigDecimal(100));
		financeDTO.setUser(userDTO);
		financeDTO.setType(typeDTO);
		
		Mockito.when(typeService.findTypeById(typeDTO.getId())).thenReturn(type);
		
		Mockito.when(userService.findUserById(userDTO.getId())).thenReturn(user);
		
		Finance finance = financeMapper.toFinance(financeDTO);
		
		assertEquals(financeDTO.getId(), finance.getId());
		assertEquals(financeDTO.getName(), finance.getName());
		assertEquals(financeDTO.getValue(), finance.getValue());
		assertEquals(financeDTO.getType().getId(), finance.getType().getId());
		assertEquals(financeDTO.getType().getName(), finance.getType().getName());
		assertEquals(financeDTO.getType().getCategory().getId(), finance.getType().getCategory().getId());
		assertEquals(financeDTO.getType().getCategory().getName(), finance.getType().getCategory().getName().name());
		assertEquals(financeDTO.getType().getUser().getId(), finance.getType().getUser().getId());
		assertEquals(financeDTO.getType().getUser().getUsername(), finance.getType().getUser().getUsername());
		assertEquals(financeDTO.getUser().getId(), finance.getUser().getId());
		assertEquals(financeDTO.getUser().getUsername(), finance.getUser().getUsername());
	}
	
	@Test
	public void MappedNewFinance_ShouldBeEquals_GivenCreateFinanceDTO() {
		
		User user = new User();
		user.setId(1L);
		
		Type type = new Type();
		type.setId(1L);
		
		CreateFinanceDTO createFinanceDTO = new CreateFinanceDTO();
		createFinanceDTO.setName("Finance");
		createFinanceDTO.setValue(new BigDecimal(100));
		createFinanceDTO.setUserId(user.getId());
		createFinanceDTO.setTypeId(type.getId());
		
		Mockito.when(userService.findUserById(user.getId())).thenReturn(user);
		
		Mockito.when(typeService.findTypeById(type.getId())).thenReturn(type);
		
		Finance finance = financeMapper.toNewFinance(createFinanceDTO);
		
		assertEquals(createFinanceDTO.getName(), finance.getName());
		assertEquals(createFinanceDTO.getValue(), finance.getValue());
		assertEquals(createFinanceDTO.getUserId(), finance.getUser().getId());
		assertEquals(createFinanceDTO.getTypeId(), finance.getType().getId());
	}
	
	@Test
	public void MappedFinanceDTO_ShouldBeNull() {
		
		Finance finance = null;
		
		FinanceDTO financeDTO = financeMapper.toFinanceDTO(finance);
		
		assertNull(financeDTO);
	}
	
	@Test
	public void MappedFinance_ShouldBeNull() {
		
		FinanceDTO financeDTO = null;
		
		Finance finance = financeMapper.toFinance(financeDTO);
		
		assertNull(finance);
	}
	
	@Test
	public void MappedNewFinance_ShouldBeNull() {
		
		CreateFinanceDTO createFinanceDTO = null;
		
		Finance finance = financeMapper.toNewFinance(createFinanceDTO);
		
		assertNull(finance);
	}
}
