package com.example.TravelAgency.Services;

import com.example.TravelAgency.Entity.Guide;
import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Repositories.GuideRepository;
import com.example.TravelAgency.Services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideService {

    private final GuideRepository guideRepository;
    private final TownService townService;

    @Autowired
    public GuideService(GuideRepository guideRepository, TownService townService) {
        this.guideRepository = guideRepository;
        this.townService = townService;
    }

    public List<Guide> getAll() {
        return guideRepository.findAll();
    }

    public Optional<Guide> getById(Long id) {
        return guideRepository.findById(id);
    }

    public Guide create(Guide guide) {
        // Получить город по ID
        Town town = townService.getById(guide.getTown().getId()).orElseThrow();
        guide.setTown(town);

        return guideRepository.save(guide);
    }

    public Guide update(Long id, Guide guide) {
        if (guideRepository.existsById(id)) {
            guide.setId(id);
            // Получить город по ID
            Town town = townService.getById(guide.getTown().getId()).orElseThrow();
            guide.setTown(town);

            return guideRepository.save(guide);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (guideRepository.existsById(id)) {
            guideRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Guide getByFirstNameAndLastName(String firstName, String lastName) {
        return guideRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Guide> getByTownTitle(String townTitle) {
        return guideRepository.findByTownTitle(townTitle);
    }
}
