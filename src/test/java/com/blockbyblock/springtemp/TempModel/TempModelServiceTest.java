package com.blockbyblock.springtemp.TempModel;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.blockbyblock.springtemp.TempModel.exception.BadRequestException;

@TestPropertySource(
  locations = "classpath:applicationtm.properties"
)
@ExtendWith(MockitoExtension.class)
public class TempModelServiceTest {
  @Mock
  private TempModelRepository tempModelRepository;
  // private AutoCloseable autoCloseable;
  private TempModelService underTest;

  @BeforeEach
  void setUp() {
    // autoCloseable = MockitoAnnotations.openMocks(this);
    underTest = new TempModelService(tempModelRepository);
  }

  // @AfterEach
  // void tearDown() throws Exception {
  //   autoCloseable.close();
  // }

  @Test
  void itShouldGetAllTempModels() {
    underTest.getTempModels();
    verify(tempModelRepository).findAll();
  }

  @Test
  void itShouldAddTempModel() {
    TempModel tempModel = new TempModel("testOne", LocalDate.now());
    underTest.addTempModel(tempModel);

    ArgumentCaptor<TempModel> tempModelArgumentCaptor = ArgumentCaptor.forClass(TempModel.class);
    verify(tempModelRepository).save(tempModelArgumentCaptor.capture());

    TempModel savedTempModel = tempModelArgumentCaptor.getValue();
    assertThat(savedTempModel).isEqualTo(tempModel);
  }

  @Test
  void itShouldThrowWhenNameIsTaken() {
    TempModel tempModel = new TempModel("testOne", LocalDate.now());

    given(tempModelRepository.existsByName(tempModel.getName())).willReturn(true);

    assertThatThrownBy(() -> underTest.addTempModel(tempModel))
        .isInstanceOf(BadRequestException.class)
        .hasMessageContaining("TempModel already exists for " + tempModel.getName());

    verify(tempModelRepository, never()).save(any());
  }
}
