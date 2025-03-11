package com.example.TravelAgency.DTO.TypeAttraction;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class TypeAttractionPostRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String title;
}
