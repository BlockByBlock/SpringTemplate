package com.blockbyblock.springtemp.TempModel;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/v1/temp")
public class TempModelController {
	@Autowired
	private TempModelService tempModelService;

	@GetMapping
	public List<TempModel> getTempModels() {
		return tempModelService.getTempModels();
	}

	@PostMapping
	public void addTempModel(@Valid @RequestBody TempModel tempModel) {
		tempModelService.addTempModel(tempModel);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteTempModel(@PathVariable("id") Long id) {
		tempModelService.deleteTempModel(id);
	}

	@PutMapping(path = "/{id}")
	public void updateTempModel(
		@PathVariable("id") Long id,
		@RequestParam(required = false) String name
		) {
		tempModelService.updateTempModel(id, name);
	}
}