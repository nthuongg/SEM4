package org.example.travel.Controller;

import org.example.travel.Model.Place;
import org.example.travel.Service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1.1/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<Place> addPlace(@RequestBody Place place) {
        return ResponseEntity.ok(placeService.save(place));
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        return ResponseEntity.ok(placeService.findAll());
    }

//    public ResponseEntity<Place> getPlaceById(int id) {
//
//    }
}
