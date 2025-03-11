package com.example.TravelAgency.Repositories;

import com.example.TravelAgency.Entity.TypeAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAttractionRepository extends JpaRepository<TypeAttraction, Long> {
    TypeAttraction findByTitle(String title);
}
