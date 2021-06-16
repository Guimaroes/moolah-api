package com.moolah.finance;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/finances")
public class FinanceController {

	@Autowired
	private FinanceService financeService;
	
	@GetMapping
	public List<FinanceDTO> findFinances(@RequestParam(required = false) Long userId) {
		if (userId != null) return financeService.findFinanceDTOsByUserId(userId);
		else return financeService.findAllFinanceDTOs();
	}
	
	@GetMapping("/{id}")
	public FinanceDTO getType(@PathVariable Long id) {
		return financeService.findFinanceDTOById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FinanceDTO add(@Valid @RequestBody CreateFinanceDTO createFinanceDTO) {
		return financeService.addFinance(createFinanceDTO);
	}
	
	@PutMapping("/{id}")
	public FinanceDTO edit(@PathVariable Long id, @Valid @RequestBody UpdateFinanceDTO updateFinanceDTO) {
		return financeService.updateFinance(id, updateFinanceDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFinance(@PathVariable Long id) {
		financeService.deleteFinanceById(id);
	}
}
