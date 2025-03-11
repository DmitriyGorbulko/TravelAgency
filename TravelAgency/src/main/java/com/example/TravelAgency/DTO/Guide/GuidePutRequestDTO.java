package com.example.TravelAgency.DTO.Guide;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class GuidePutRequestDTO {
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Size(max = 500)
    private String description;

    private Long townId;

    private String telegramUsername;
}
