package com.example.TravelAgency.Controllers;

import com.example.TravelAgency.DTO.Guide.GuideGetResponseDTO;
import com.example.TravelAgency.DTO.Guide.GuidePostRequestDTO;
import com.example.TravelAgency.DTO.Guide.GuidePostResponseDTO;
import com.example.TravelAgency.DTO.Guide.GuidePutRequestDTO;
import com.example.TravelAgency.DTO.Guide.GuidePutResponseDTO;
import com.example.TravelAgency.DTO.Guide.GuideDeleteResponseDTO;
import com.example.TravelAgency.Entity.Guide;
import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Services.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guides")
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @GetMapping
    public ResponseEntity<List<GuideGetResponseDTO>> getAll() {
        List<Guide> guides = guideService.getAll();
        List<GuideGetResponseDTO> responseDTOs = guides.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuideGetResponseDTO> getById(@PathVariable Long id) {
        return guideService.getById(id)
                .map(this::toGetResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<GuidePostResponseDTO> create(@RequestBody GuidePostRequestDTO requestDTO) {
        Guide guide = toEntity(requestDTO);
        Guide createdGuide = guideService.create(guide);
        return new ResponseEntity<>(toPostResponseDTO(createdGuide), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuidePutResponseDTO> update(@PathVariable Long id, @RequestBody GuidePutRequestDTO requestDTO) {
        Guide guide = toEntity(requestDTO);
        Guide updatedGuide = guideService.update(id, guide);
        return updatedGuide != null
                ? ResponseEntity.ok(toPutResponseDTO(updatedGuide))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuideDeleteResponseDTO> delete(@PathVariable Long id) {
        boolean deleted = guideService.delete(id);
        GuideDeleteResponseDTO responseDTO = new GuideDeleteResponseDTO();
        responseDTO.setMessage(deleted ? "Удалено успешно" : "Не найдено");
        return deleted
                ? ResponseEntity.ok(responseDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    @GetMapping("/firstName/{firstName}/lastName/{lastName}")
    public ResponseEntity<GuideGetResponseDTO> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        Guide guide = guideService.getByFirstNameAndLastName(firstName, lastName);
        return guide != null
                ? ResponseEntity.ok(toGetResponseDTO(guide))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private GuideGetResponseDTO toGetResponseDTO(Guide entity) {
        GuideGetResponseDTO dto = new GuideGetResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setTownId(entity.getTown().getId());
        dto.setDescription(entity.getDescription());
        dto.setTelegramUsername(entity.getTelegramUsername());
        return dto;
    }

    private GuidePostResponseDTO toPostResponseDTO(Guide entity) {
        GuidePostResponseDTO dto = new GuidePostResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setTownId(entity.getTown().getId());
        dto.setDescription(entity.getDescription());
        dto.setTelegramUsername(entity.getTelegramUsername());
        return dto;
    }

    private GuidePutResponseDTO toPutResponseDTO(Guide entity) {
        GuidePutResponseDTO dto = new GuidePutResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setTownId(entity.getTown().getId());
        dto.setDescription(entity.getDescription());
        dto.setTelegramUsername(entity.getTelegramUsername());
        return dto;
    }

    private Guide toEntity(GuidePostRequestDTO requestDTO) {
        Guide entity = new Guide();
        entity.setFirstName(requestDTO.getFirstName());
        entity.setLastName(requestDTO.getLastName());
        entity.setDescription(requestDTO.getDescription());
        entity.setTelegramUsername(requestDTO.getTelegramUsername()); // Исправлена опечатка
        // Получить город по ID
        Town town = new Town();
        town.setId(requestDTO.getTownId());
        entity.setTown(town);
        return entity;
    }

    private Guide toEntity(GuidePutRequestDTO requestDTO) {
        Guide entity = new Guide();
        entity.setFirstName(requestDTO.getFirstName());
        entity.setLastName(requestDTO.getLastName());
        entity.setDescription(requestDTO.getDescription());
        entity.setTelegramUsername(requestDTO.getTelegramUsername()); // Исправлена опечатка
        // Получить город по ID
        Town town = new Town();
        town.setId(requestDTO.getTownId());
        entity.setTown(town);
        return entity;
    }

    @GetMapping("/town/{townTitle}")
    public ResponseEntity<List<GuideGetResponseDTO>> getByTownTitle(@PathVariable String townTitle) {
        List<Guide> guides = guideService.getByTownTitle(townTitle);
        List<GuideGetResponseDTO> responseDTOs = guides.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}
