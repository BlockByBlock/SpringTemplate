package com.blockbyblock.springtemp.TempModel;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempModel {
	@Id
	@SequenceGenerator(name = "temp_model_seq", sequenceName = "temp_model_seq", allocationSize = 1)
	@GeneratedValue(generator = "temp_model_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate createdTime;

	public TempModel(String name, LocalDate createdTime) {
		this.name = name;
		this.createdTime = createdTime;
	}
}
