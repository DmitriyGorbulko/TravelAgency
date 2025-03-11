package com.example.TravelAgency.Repositories;

import com.example.TravelAgency.Entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByTitle(String title);
}
