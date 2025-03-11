package com.example.TravelAgency.Repositories;

import com.example.TravelAgency.Entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {
    Guide findByFirstNameAndLastName(String firstName, String lastName);
    @Query("SELECT g FROM Guide g JOIN g.town t WHERE t.title = :townTitle")
    List<Guide> findByTownTitle(@Param("townTitle") String townTitle);
}
