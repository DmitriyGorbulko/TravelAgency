package com.example.TravelAgency.Controllers;

import com.example.TravelAgency.DTO.Tour.*;
import com.example.TravelAgency.Entity.*;
import com.example.TravelAgency.Services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping
    public ResponseEntity<List<TourGetResponseDTO>> getAll() {
        List<Tour> tours = tourService.getAll();
        List<TourGetResponseDTO> responseDTOs = tours.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourGetResponseDTO> getById(@PathVariable Long id) {
        return tourService.getById(id)
                .map(this::toGetResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TourPostResponseDTO> create(@RequestBody TourPostRequestDTO requestDTO) {
        Tour tour = toEntity(requestDTO);
        Tour createdTour = tourService.create(tour);
        return new ResponseEntity<>(toPostResponseDTO(createdTour), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourPutResponseDTO> update(@PathVariable Long id, @RequestBody TourPutRequestDTO requestDTO) {
        Tour tour = toEntity(requestDTO);
        Tour updatedTour = tourService.update(id, tour);
        return updatedTour != null
                ? ResponseEntity.ok(toPutResponseDTO(updatedTour))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourDeleteResponseDTO> delete(@PathVariable Long id) {
        boolean deleted = tourService.delete(id);
        TourDeleteResponseDTO responseDTO = new TourDeleteResponseDTO();
        responseDTO.setMessage(deleted ? "Удалено успешно" : "Не найдено");
        return deleted
                ? ResponseEntity.ok(responseDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    @GetMapping("/town/{townTitle}")
    public ResponseEntity<List<TourGetResponseDTO>> getByTownTitle(@PathVariable String townTitle) {
        List<Tour> tours = tourService.getByTownTitle(townTitle);
        List<TourGetResponseDTO> responseDTOs = tours.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    private TourGetResponseDTO toGetResponseDTO(Tour entity) {
        TourGetResponseDTO dto = new TourGetResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTownId(entity.getTown().getId());
        dto.setPrice(entity.getPrice());
        dto.setUrl(entity.getUrl());
        return dto;
    }

    private TourPostResponseDTO toPostResponseDTO(Tour entity) {
        TourPostResponseDTO dto = new TourPostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTownId(entity.getTown().getId());
        dto.setPrice(entity.getPrice());
        dto.setUrl(entity.getUrl());
        return dto;
    }

    private TourPutResponseDTO toPutResponseDTO(Tour entity) {
        TourPutResponseDTO dto = new TourPutResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTownId(entity.getTown().getId());
        dto.setPrice(entity.getPrice());
        dto.setUrl(entity.getUrl());
        return dto;
    }

    private Tour toEntity(TourPostRequestDTO requestDTO) {
        Tour entity = new Tour();
        entity.setTitle(requestDTO.getTitle());
        entity.setDescription(requestDTO.getDescription());
        entity.setPrice(requestDTO.getPrice());
        entity.setUrl(requestDTO.getUrl());

        // Get town by ID
        Town town = new Town();
        town.setId(requestDTO.getTownId());
        entity.setTown(town);

        return entity;
    }

    private Tour toEntity(TourPutRequestDTO requestDTO) {
        Tour entity = new Tour();
        entity.setTitle(requestDTO.getTitle());
        entity.setDescription(requestDTO.getDescription());
        entity.setPrice(requestDTO.getPrice());
        entity.setUrl(requestDTO.getUrl());

        // Get town by ID
        Town town = new Town();
        town.setId(requestDTO.getTownId());
        entity.setTown(town);

        return entity;
    }
}
