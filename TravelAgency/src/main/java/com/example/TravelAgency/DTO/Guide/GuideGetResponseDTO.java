package com.example.TravelAgency.DTO.Guide;

import lombok.Data;

@Data
public class GuideGetResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long townId;
    private String description;
    private String telegramUsername;
}
