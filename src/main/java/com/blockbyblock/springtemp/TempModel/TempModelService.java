package com.blockbyblock.springtemp.TempModel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempModelService {
  
	@Autowired
	private TempModelRepository tempModelRepository;

	public List<TempModel> getTempModels() {
		return tempModelRepository.findAll();
	}
}
