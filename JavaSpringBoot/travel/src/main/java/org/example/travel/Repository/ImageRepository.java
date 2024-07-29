package org.example.travel.Repository;

import org.example.travel.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPlaceId(Long placeId);
    List<Image> findByGuideId(Long guideId);
}
