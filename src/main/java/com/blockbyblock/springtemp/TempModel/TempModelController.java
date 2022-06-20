package com.blockbyblock.springtemp.TempModel;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/v1/temp")
public class TempModelController {

    @Autowired
    private TempModelService tempModelService;

    @GetMapping("/temps")
	public List<TempModel> getTempModels() {
		return tempModelService.getTempModels();
	}
}
