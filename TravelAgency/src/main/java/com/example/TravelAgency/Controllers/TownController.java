package com.example.TravelAgency.Controllers;

import com.example.TravelAgency.DTO.Town.TownGetResponseDTO;
import com.example.TravelAgency.DTO.Town.TownPostRequestDTO;
import com.example.TravelAgency.DTO.Town.TownPostResponseDTO;
import com.example.TravelAgency.DTO.Town.TownPutRequestDTO;
import com.example.TravelAgency.DTO.Town.TownPutResponseDTO;
import com.example.TravelAgency.DTO.Town.TownDeleteResponseDTO;
import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Services.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/towns")
@RequiredArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping
    public ResponseEntity<List<TownGetResponseDTO>> getAll() {
        List<Town> towns = townService.getAll();
        List<TownGetResponseDTO> responseDTOs = towns.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TownGetResponseDTO> getById(@PathVariable Long id) {
        return townService.getById(id)
                .map(this::toGetResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TownPostResponseDTO> create(@RequestBody TownPostRequestDTO requestDTO) {
        Town town = toEntity(requestDTO);
        Town createdTown = townService.create(town);
        return new ResponseEntity<>(toPostResponseDTO(createdTown), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TownPutResponseDTO> update(@PathVariable Long id, @RequestBody TownPutRequestDTO requestDTO) {
        Town town = toEntity(id, requestDTO);
        Town updatedTown = townService.update(id, town);
        return updatedTown != null
                ? ResponseEntity.ok(toPutResponseDTO(updatedTown))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TownDeleteResponseDTO> delete(@PathVariable Long id) {
        boolean deleted = townService.delete(id);
        TownDeleteResponseDTO responseDTO = new TownDeleteResponseDTO();
        responseDTO.setMessage(deleted ? "Удалено успешно" : "Не найдено");
        return deleted
                ? ResponseEntity.ok(responseDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<TownGetResponseDTO> getByTitle(@PathVariable String title) {
        Town town = townService.getByTitle(title);
        return town != null
                ? ResponseEntity.ok(toGetResponseDTO(town))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private TownGetResponseDTO toGetResponseDTO(Town entity) {
        TownGetResponseDTO dto = new TownGetResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private TownPostResponseDTO toPostResponseDTO(Town entity) {
        TownPostResponseDTO dto = new TownPostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private TownPutResponseDTO toPutResponseDTO(Town entity) {
        TownPutResponseDTO dto = new TownPutResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private Town toEntity(TownPostRequestDTO requestDTO) {
        Town entity = new Town();
        entity.setTitle(requestDTO.getTitle());
        return entity;
    }

    private Town toEntity(Long id, TownPutRequestDTO requestDTO) {
        Town entity = new Town();
        entity.setId(id);
        entity.setTitle(requestDTO.getTitle());
        return entity;
    }
}
