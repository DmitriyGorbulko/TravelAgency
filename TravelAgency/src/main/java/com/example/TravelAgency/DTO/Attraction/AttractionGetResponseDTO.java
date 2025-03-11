package com.example.TravelAgency.DTO.Attraction;

import lombok.Data;

@Data
public class AttractionGetResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String town;
    private String typeAttraction;
}
