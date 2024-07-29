package org.example.travel.Repository;

import org.example.travel.Model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByNameContainingIgnoreCase(String name);

    List<Place> findByGuideId(Long guideId);
}







