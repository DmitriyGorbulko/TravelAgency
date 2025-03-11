package com.example.TravelAgency.Controllers;

import com.example.TravelAgency.DTO.Attraction.AttractionGetResponseDTO;
import com.example.TravelAgency.DTO.Attraction.AttractionPostRequestDTO;
import com.example.TravelAgency.DTO.Attraction.AttractionPostResponseDTO;
import com.example.TravelAgency.DTO.Attraction.AttractionPutRequestDTO;
import com.example.TravelAgency.DTO.Attraction.AttractionPutResponseDTO;
import com.example.TravelAgency.DTO.Attraction.AttractionDeleteResponseDTO;
import com.example.TravelAgency.Entity.Attraction;
import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Entity.TypeAttraction;
import com.example.TravelAgency.Services.AttractionService;
import com.example.TravelAgency.Services.TownService;
import com.example.TravelAgency.Services.TypeAttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;
    private final TownService townService;
    private final TypeAttractionService typeAttractionService;

    @GetMapping
    public ResponseEntity<List<AttractionGetResponseDTO>> getAll() {
        List<Attraction> attractions = attractionService.getAll();
        List<AttractionGetResponseDTO> responseDTOs = attractions.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionGetResponseDTO> getById(@PathVariable Long id) {
        return attractionService.getById(id)
                .map(this::toGetResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<AttractionPostResponseDTO> create(@RequestBody AttractionPostRequestDTO requestDTO) {
        Attraction attraction = toEntity(requestDTO);
        Attraction createdAttraction = attractionService.create(attraction);
        return new ResponseEntity<>(toPostResponseDTO(createdAttraction), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttractionPutResponseDTO> update(@PathVariable Long id, @RequestBody AttractionPutRequestDTO requestDTO) {
        Attraction attraction = toEntity(requestDTO);
        Attraction updatedAttraction = attractionService.update(id, attraction);
        return updatedAttraction != null
                ? ResponseEntity.ok(toPutResponseDTO(updatedAttraction))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttractionDeleteResponseDTO> delete(@PathVariable Long id) {
        boolean deleted = attractionService.delete(id);
        AttractionDeleteResponseDTO responseDTO = new AttractionDeleteResponseDTO();
        responseDTO.setMessage(deleted ? "Удалено успешно" : "Не найдено");
        return deleted
                ? ResponseEntity.ok(responseDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<AttractionGetResponseDTO> getByTitle(@PathVariable String title) {
        Attraction attraction = attractionService.getByTitle(title);
        return attraction != null
                ? ResponseEntity.ok(toGetResponseDTO(attraction))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private AttractionGetResponseDTO toGetResponseDTO(Attraction entity) {
        AttractionGetResponseDTO dto = new AttractionGetResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTown(entity.getTown().getTitle());
        dto.setTypeAttraction(entity.getTypeAttraction().getTitle());
        return dto;
    }

    private AttractionPostResponseDTO toPostResponseDTO(Attraction entity) {
        AttractionPostResponseDTO dto = new AttractionPostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTownId(entity.getTown().getId());
        dto.setTypeAttractionId(entity.getTypeAttraction().getId());
        return dto;
    }

    private AttractionPutResponseDTO toPutResponseDTO(Attraction entity) {
        AttractionPutResponseDTO dto = new AttractionPutResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTownId(entity.getTown().getId());
        dto.setTypeAttractionId(entity.getTypeAttraction().getId());
        return dto;
    }

    private Attraction toEntity(AttractionPostRequestDTO requestDTO) {
        Attraction entity = new Attraction();
        entity.setTitle(requestDTO.getTitle());
        entity.setDescription(requestDTO.getDescription());
        // Получить город и тип достопримечательности по ID
        Town town = new Town();
        town.setId(requestDTO.getTownId());
        entity.setTown(town);

        TypeAttraction typeAttraction = new TypeAttraction();
        typeAttraction.setId(requestDTO.getTypeAttractionId());
        entity.setTypeAttraction(typeAttraction);

        return entity;
    }

    private Attraction toEntity(AttractionPutRequestDTO requestDTO) {
        Attraction entity = new Attraction();
        entity.setTitle(requestDTO.getTitle());
        entity.setDescription(requestDTO.getDescription());
        // Получить город и тип достопримечательности по ID
        Town town = new Town();
        town.setId(requestDTO.getTownId());
        entity.setTown(town);

        TypeAttraction typeAttraction = new TypeAttraction();
        typeAttraction.setId(requestDTO.getTypeAttractionId());
        entity.setTypeAttraction(typeAttraction);

        return entity;
    }

    @GetMapping("/town/{townTitle}")
    public ResponseEntity<List<AttractionGetResponseDTO>> getByTownTitle(@PathVariable String townTitle) {
        List<Attraction> attractions = attractionService.getByTownTitle(townTitle);
        List<AttractionGetResponseDTO> responseDTOs = attractions.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }


}
