package com.blockbyblock.springtemp.TempModel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TempModelConfig {

  @Bean
  CommandLineRunner commandLineRunner(TempModelRepository repository) {
    return args -> {
      TempModel testModelOne = new TempModel(
          "testOne",
          LocalDate.now()
      );

      TempModel testModelTwo = new TempModel(
          "testTwo",
          LocalDate.now()
      );

      repository.saveAll(
        List.of(testModelOne, testModelTwo)
      );
    };
  }
}
