package com.example.TravelAgency.Controllers;

import com.example.TravelAgency.DTO.TypeAttraction.*;
import com.example.TravelAgency.Entity.TypeAttraction;
import com.example.TravelAgency.Services.TypeAttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/type-attractions")
@RequiredArgsConstructor
public class TypeAttractionController {

    private final TypeAttractionService typeAttractionService;

    @GetMapping
    public ResponseEntity<List<TypeAttractionGetResponseDTO>> getAll() {
        List<TypeAttraction> attractions = typeAttractionService.getAll();
        List<TypeAttractionGetResponseDTO> responseDTOs = attractions.stream()
                .map(this::toGetResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeAttractionGetResponseDTO> getById(@PathVariable Long id) {
        return typeAttractionService.getById(id)
                .map(this::toGetResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TypeAttractionPostResponseDTO> create(@RequestBody TypeAttractionPostRequestDTO requestDTO) {
        TypeAttraction typeAttraction = toEntity(requestDTO);
        TypeAttraction createdAttraction = typeAttractionService.create(typeAttraction);
        return new ResponseEntity<>(toPostResponseDTO(createdAttraction), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeAttractionPutResponseDTO> update(@PathVariable Long id, @RequestBody TypeAttractionPutRequestDTO requestDTO) {
        TypeAttraction typeAttraction = toEntity(requestDTO);
        TypeAttraction updatedAttraction = typeAttractionService.update(id, typeAttraction);
        return updatedAttraction != null
                ? ResponseEntity.ok(toPutResponseDTO(updatedAttraction))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeAttractionDeleteResponseDTO> delete(@PathVariable Long id) {
        boolean deleted = typeAttractionService.delete(id);
        TypeAttractionDeleteResponseDTO responseDTO = new TypeAttractionDeleteResponseDTO();
        responseDTO.setMessage(deleted ? "Удалено успешно" : "Не найдено");
        return deleted
                ? ResponseEntity.ok(responseDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    private TypeAttractionGetResponseDTO toGetResponseDTO(TypeAttraction entity) {
        TypeAttractionGetResponseDTO dto = new TypeAttractionGetResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private TypeAttractionPostResponseDTO toPostResponseDTO(TypeAttraction entity) {
        TypeAttractionPostResponseDTO dto = new TypeAttractionPostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private TypeAttractionPutResponseDTO toPutResponseDTO(TypeAttraction entity) {
        TypeAttractionPutResponseDTO dto = new TypeAttractionPutResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private TypeAttraction toEntity(TypeAttractionPostRequestDTO requestDTO) {
        TypeAttraction entity = new TypeAttraction();
        entity.setTitle(requestDTO.getTitle());
        return entity;
    }

    private TypeAttraction toEntity(TypeAttractionPutRequestDTO requestDTO) {
        TypeAttraction entity = new TypeAttraction();
        entity.setTitle(requestDTO.getTitle());
        return entity;
    }
}
