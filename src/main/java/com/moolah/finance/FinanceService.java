package com.moolah.finance;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moolah.type.TypeService;
import com.moolah.user.UserService;

@Service
public class FinanceService {

	@Autowired
	private FinanceRepository financeRepository;
		
	@Autowired
	private FinanceMapper financeMapper;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private UserService userService;
	
	public FinanceDTO addFinance(CreateFinanceDTO createFinanceDTO) {
		return financeMapper.toFinanceDTO(financeRepository.saveAndFlush(financeMapper.toNewFinance(createFinanceDTO)));
	}
	
	public Finance findFinanceById(Long id) {
		return financeRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Finance not found.");
		});
	}
	
	public FinanceDTO findFinanceDTOById(Long id) {
		return financeMapper.toFinanceDTO(findFinanceById(id));
	}
	
	public List<Finance> findAllFinances() {
		return financeRepository.findAll();
	}
	
	public List<FinanceDTO> findAllFinanceDTOs() {
		return financeMapper.toFinanceDTOList(findAllFinances());
	}
	
	public List<Finance> findFinancesByUserId(Long userId) {
		return financeRepository.findAllByUserIdOrderByIdDesc(userId);
	}
	
	public List<FinanceDTO> findFinanceDTOsByUserId(Long userId) {
		return financeMapper.toFinanceDTOList(findFinancesByUserId(userId));
	}
	
	public Finance updateFinance(Finance finance) {
		return financeRepository.saveAndFlush(finance);
	}
	
	public FinanceDTO updateFinance(Long id, UpdateFinanceDTO updateFinanceDTO) {
		Finance finance = findFinanceById(id);
		
		finance.setName(updateFinanceDTO.getName());
		finance.setValue(updateFinanceDTO.getValue());
		finance.setType(typeService.findTypeById(updateFinanceDTO.getTypeId()));
		finance.setUser(userService.findUserById(updateFinanceDTO.getUserId()));
		
		return financeMapper.toFinanceDTO(updateFinance(finance));
	}
	
	public void deleteFinanceById(Long id) {
		financeRepository.deleteById(id);
	}
}
