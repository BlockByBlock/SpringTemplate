package com.blockbyblock.springtemp.Integration;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.javafaker.Faker;
import com.blockbyblock.springtemp.TempModel.TempModel;
import com.blockbyblock.springtemp.TempModel.TempModelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:applicationit.properties"
)
@AutoConfigureMockMvc
public class TempModelIT {

  @Autowired
  private WebApplicationContext context;
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TempModelRepository tempModelRepository;

  private final Faker faker = new Faker();

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithUserDetails(value = "user")
  void itShouldAddNewTempModel() throws Exception {
    String name = faker.name().fullName();

    TempModel tempModel = new TempModel(
      name,
      LocalDate.now()
    );

    ResultActions resultActions = mockMvc.perform(post("/api/v1/temp")
      .with(csrf())
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(tempModel)));

    resultActions.andExpect(status().isOk());
    List<TempModel> tempModels = tempModelRepository.findAll();
    assertThat(tempModels)
      .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
      .contains(tempModel);
  }

  @Test
  @WithUserDetails(value = "user")
  void itShouldDeleteTempModel() throws Exception {
    TempModel tempModel = new TempModel(
      faker.name().fullName(),
      LocalDate.now()
    );
    
    mockMvc.perform(post("/api/v1/temp")
      .with(csrf())
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(tempModel)))
      .andExpect(status().isOk());

    MvcResult getTempModelsResult = mockMvc.perform(get("/api/v1/temp")
      .with(csrf())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();

      String contentAsString = getTempModelsResult
        .getResponse()
        .getContentAsString();

      List<TempModel> tempModels = objectMapper.readValue(
        contentAsString,
        new TypeReference<>() {
        }
      );

      long id = tempModels.stream()
        .filter(t -> t.getName().equals(tempModel.getName()))
        .map(TempModel::getId)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No temp model found"));

      ResultActions resultActions = mockMvc.perform(delete("/api/v1/temp/" + id).with(csrf()));

      resultActions.andExpect(status().isOk());
      boolean exists = tempModelRepository.existsById(id);
      assertThat(exists).isFalse();
    }
}
