package com.example.TravelAgency.Repositories;

import com.example.TravelAgency.Entity.Attraction;
import com.example.TravelAgency.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Tour findByTitle(String title);
    @Query("SELECT t FROM Tour t WHERE t.town.title = :townTitle")
    List<Tour> findByTownTitle(@Param("townTitle") String townTitle);

}
