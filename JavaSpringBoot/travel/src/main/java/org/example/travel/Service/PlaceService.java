package org.example.travel.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.travel.Model.Place;
import org.example.travel.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public List<Place> findByGuideId(Long guideId) {
        return placeRepository.findByGuideId(guideId);
    }

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    public Place save(Place place) {
        return placeRepository.save(place);
    }

    public Place updatePlace(Long id, Place placeDetails) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Place not found"));
        place.setName(placeDetails.getName());
        place.setDescription(placeDetails.getDescription());
        place.setLocation(placeDetails.getLocation());
        place.setGuide(placeDetails.getGuide());
        return placeRepository.save(place);
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }


    public List<Place> findByName(String name) {
        return placeRepository.findByNameContainingIgnoreCase(name);
    }
}
