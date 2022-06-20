package com.blockbyblock.springtemp.TempModel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempModel {
    private Long id;
    private String name;
    private LocalDate createdTime;

    public TempModel(String name, LocalDate createdTime) {
        this.name = name;
        this.createdTime = createdTime;
    }
}
