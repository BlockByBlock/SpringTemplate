package com.blockbyblock.springtemp.TempModel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TempModelService {
    public List<TempModel> getTempModels() {
		return List.of(
				new TempModel("temp1", LocalDate.now()),
				new TempModel("temp2", LocalDate.now()),
				new TempModel("temp3", LocalDate.now())
		);
	}
}
