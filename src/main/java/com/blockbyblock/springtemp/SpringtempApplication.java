package com.blockbyblock.springtemp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blockbyblock.springtemp.TempModel.TempModel;

@SpringBootApplication
@RestController
public class SpringTempApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTempApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/temp")
	public List<TempModel> temp() {
		return List.of(
				new TempModel("temp1", LocalDate.now()),
				new TempModel("temp2", LocalDate.now()),
				new TempModel("temp3", LocalDate.now())
		);
	}
}
