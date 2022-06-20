package com.blockbyblock.springtemp.TempModel;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TempModelServiceTest {
  @Mock
  private TempModelRepository tempModelRepository;
  private AutoCloseable autoCloseable;
  private TempModelService tempModelService;

  @BeforeEach
  void setUp() {
    autoCloseable = MockitoAnnotations.openMocks(this);
    tempModelService = new TempModelService(tempModelRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    autoCloseable.close();
  }

  @Test
  void itShouldGetAllTempModels() {
    tempModelService.getTempModels();
    verify(tempModelRepository).findAll();
  }

  @Test
  void itShouldAddTempModel() {
    TempModel tempModel = new TempModel("testOne", LocalDate.now());
    tempModelService.addTempModel(tempModel);

    ArgumentCaptor<TempModel> tempModelArgumentCaptor = ArgumentCaptor.forClass(TempModel.class);
    verify(tempModelRepository).save(tempModelArgumentCaptor.capture());

    TempModel savedTempModel = tempModelArgumentCaptor.getValue();
    assertThat(savedTempModel).isEqualTo(tempModel);
  }
}
