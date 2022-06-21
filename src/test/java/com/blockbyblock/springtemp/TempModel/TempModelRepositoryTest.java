package com.blockbyblock.springtemp.TempModel;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TempModelRepositoryTest {

  @Autowired
  private TempModelRepository underTest;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void itShouldSaveTempModel() {
    String name = "test1";
    TempModel tempModel = new TempModel(
      name, LocalDate.now()
    );

    underTest.save(tempModel);
    boolean expected = underTest.findByName(name).isPresent();

    assertThat(expected).isTrue();
  }

  @Test
  @Disabled("This test is disabled because it is not implemented yet")
  void itShouldFailIfTempModelNameDoesNotExists() {
    String name = "test1";

    boolean expected = underTest.findByName(name).isPresent();

    assertThat(expected).isFalse();
  }
}
