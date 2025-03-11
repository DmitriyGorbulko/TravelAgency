package com.example.TravelAgency.DTO.Attraction;

import lombok.Data;

@Data
public class AttractionPostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Long townId;
    private Long typeAttractionId;
}
