package com.example.TravelAgency.Services;

import com.example.TravelAgency.Entity.Attraction;
import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Entity.TypeAttraction;
import com.example.TravelAgency.Repositories.AttractionRepository;
import com.example.TravelAgency.Services.TownService;
import com.example.TravelAgency.Services.TypeAttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final TownService townService;
    private final TypeAttractionService typeAttractionService;

    @Autowired
    public AttractionService(AttractionRepository attractionRepository, TownService townService, TypeAttractionService typeAttractionService) {
        this.attractionRepository = attractionRepository;
        this.townService = townService;
        this.typeAttractionService = typeAttractionService;
    }

    public List<Attraction> getAll() {
        return attractionRepository.findAll();
    }

    public Optional<Attraction> getById(Long id) {
        return attractionRepository.findById(id);
    }

    public Attraction create(Attraction attraction) {
        // Получить город и тип достопримечательности по ID
        Town town = townService.getById(attraction.getTown().getId()).orElseThrow();
        TypeAttraction typeAttraction = typeAttractionService.getById(attraction.getTypeAttraction().getId()).orElseThrow();
        attraction.setTown(town);
        attraction.setTypeAttraction(typeAttraction);

        return attractionRepository.save(attraction);
    }

    public Attraction update(Long id, Attraction attraction) {
        if (attractionRepository.existsById(id)) {
            attraction.setId(id);
            // Получить город и тип достопримечательности по ID
            Town town = townService.getById(attraction.getTown().getId()).orElseThrow();
            TypeAttraction typeAttraction = typeAttractionService.getById(attraction.getTypeAttraction().getId()).orElseThrow();
            attraction.setTown(town);
            attraction.setTypeAttraction(typeAttraction);

            return attractionRepository.save(attraction);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (attractionRepository.existsById(id)) {
            attractionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Attraction getByTitle(String title) {
        return attractionRepository.findByTitle(title);
    }

    public List<Attraction> getByTownTitle(String townTitle) {
        return attractionRepository.findByTownTitle(townTitle);
    }
}
