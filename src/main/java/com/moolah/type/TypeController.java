package com.moolah.type;

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
@RequestMapping("/types")
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	@GetMapping
	public List<TypeDTO> findTypes(@RequestParam(required = false) Long userId) {
		if (userId != null) return typeService.findTypeDTOsByUserId(userId);
		else return typeService.findAllTypeDTOs();
	}
	
	@GetMapping("/{id}")
	public TypeDTO getType(@PathVariable Long id) {
		return typeService.findTypeDTOById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TypeDTO add(@Valid @RequestBody CreateTypeDTO createTypeDTO) {
		return typeService.addType(createTypeDTO);
	}
	
	@PutMapping("/{id}")
	public TypeDTO edit(@PathVariable Long id, @Valid @RequestBody UpdateTypeDTO updateTypeDTO) {
		return typeService.updateType(id, updateTypeDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteType(@PathVariable Long id) {
		typeService.deleteTypeById(id);
	}
}
