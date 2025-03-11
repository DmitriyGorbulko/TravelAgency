package com.example.TravelAgency.DTO.Tour;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Data
public class TourPutRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    private Long townId;

    private BigDecimal price;

    @Size(max = 200)
    private String url;
}
