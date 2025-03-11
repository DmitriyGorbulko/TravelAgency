package com.example.TravelAgency.DTO.Attraction;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class AttractionPutRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    private Long townId;

    private Long typeAttractionId;
}
