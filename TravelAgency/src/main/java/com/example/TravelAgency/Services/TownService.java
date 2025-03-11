package com.example.TravelAgency.Services;

import com.example.TravelAgency.Entity.Town;
import com.example.TravelAgency.Repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public List<Town> getAll() {
        return townRepository.findAll();
    }

    public Optional<Town> getById(Long id) {
        return townRepository.findById(id);
    }

    public Town create(Town town) {
        return townRepository.save(town);
    }

    public Town update(Long id, Town town) {
        if (townRepository.existsById(id)) {
            town.setId(id);
            return townRepository.save(town);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (townRepository.existsById(id)) {
            townRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Town getByTitle(String title) {
        return townRepository.findByTitle(title);
    }
}
