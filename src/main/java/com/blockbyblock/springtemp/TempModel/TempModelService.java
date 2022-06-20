package com.blockbyblock.springtemp.TempModel;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import com.blockbyblock.springtemp.TempModel.exception.BadRequestException;
import com.blockbyblock.springtemp.TempModel.exception.NotFoundException;

@AllArgsConstructor
@Service
public class TempModelService {
  
	@Autowired
	private TempModelRepository tempModelRepository;

	public List<TempModel> getTempModels() {
		return tempModelRepository.findAll();
	}

	public void addTempModel(TempModel tempModel) {
		Optional<TempModel> existingTempModel = tempModelRepository
			.findByName(tempModel.getName());

		if (existingTempModel.isPresent()) {
			throw new BadRequestException("TempModel already exists");
		}
	
		tempModelRepository.save(tempModel);
	}

	public void deleteTempModel(Long id) {
		boolean exists = tempModelRepository.existsById(id);
		if (!exists) {
			throw new NotFoundException("TempModel does not exist for id: " + id);
		}

		tempModelRepository.deleteById(id);
	}

	@Transactional
	public void updateTempModel(Long id, String name) {
		TempModel existingTempModel = tempModelRepository
			.findById(id)
			.orElseThrow(() -> new NotFoundException("TempModel does not exist for id: " + id));
		
		if (name != null 
			&& name.length() > 0
			&& !Objects.equals(name, existingTempModel.getName())) {
			existingTempModel.setName(name);
		}
	}
}
