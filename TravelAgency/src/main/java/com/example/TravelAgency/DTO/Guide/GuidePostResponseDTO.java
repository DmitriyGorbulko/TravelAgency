package com.example.TravelAgency.DTO.Guide;

import lombok.Data;

@Data
public class GuidePostResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long townId;
    private String description;
    private String telegramUsername;
}
