package com.example.TravelAgency.DTO.Tour;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TourPostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Long townId;
    private BigDecimal price;
    private String url;
}
