package com.example.TravelAgency.Repositories;

import com.example.TravelAgency.Entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Attraction findByTitle(String title);
    @Query("SELECT a FROM Attraction a JOIN a.town t WHERE t.title = :townTitle")
    List<Attraction> findByTownTitle(@Param("townTitle") String townTitle);
}
