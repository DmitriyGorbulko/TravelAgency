package com.example.TravelAgency.Services;

import com.example.TravelAgency.Entity.Tour;
import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TownService townService;

    @Autowired
    public TourService(TourRepository tourRepository, TownService townService) {
        this.tourRepository = tourRepository;
        this.townService = townService;
    }

    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    public Optional<Tour> getById(Long id) {
        return tourRepository.findById(id);
    }

    public Tour create(Tour tour) {
        // Get town by ID
        Town town = townService.getById(tour.getTown().getId())
                .orElseThrow(() -> new RuntimeException("Town not found"));
        tour.setTown(town);
        return tourRepository.save(tour);
    }

    public Tour update(Long id, Tour tour) {
        if (tourRepository.existsById(id)) {
            tour.setId(id);
            // Get town by ID
            Town town = townService.getById(tour.getTown().getId())
                    .orElseThrow(() -> new RuntimeException("Town not found"));
            tour.setTown(town);
            return tourRepository.save(tour);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (tourRepository.existsById(id)) {
            tourRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Tour> getByTownTitle(String townTitle) {
        return tourRepository.findByTownTitle(townTitle);
    }
}
