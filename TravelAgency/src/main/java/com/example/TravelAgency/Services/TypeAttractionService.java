package com.example.TravelAgency.Services;

import com.example.TravelAgency.Entity.TypeAttraction;
import com.example.TravelAgency.Repositories.TypeAttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAttractionService {

    private final TypeAttractionRepository typeAttractionRepository;

    @Autowired
    public TypeAttractionService(TypeAttractionRepository typeAttractionRepository) {
        this.typeAttractionRepository = typeAttractionRepository;
    }

    public List<TypeAttraction> getAll() {
        return typeAttractionRepository.findAll();
    }

    public Optional<TypeAttraction> getById(Long id) {
        return typeAttractionRepository.findById(id);
    }

    public TypeAttraction create(TypeAttraction typeAttraction) {
        return typeAttractionRepository.save(typeAttraction);
    }

    public TypeAttraction update(Long id, TypeAttraction typeAttraction) {
        if (typeAttractionRepository.existsById(id)) {
            typeAttraction.setId(id);
            return typeAttractionRepository.save(typeAttraction);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (typeAttractionRepository.existsById(id)) {
            typeAttractionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
