package com.example.TravelAgency.DTO.Town;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class TownPutRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String title;

    private Long townId; // Добавьте это поле
}
