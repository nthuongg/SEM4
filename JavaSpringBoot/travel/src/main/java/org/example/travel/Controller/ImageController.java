package org.example.travel.Controller;

import org.example.travel.Model.Image;
import org.example.travel.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1.1/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/place/{placeId}")
    public ResponseEntity<List<Image>> getImagesByPlaceId(@PathVariable Long placeId) {
        return ResponseEntity.ok(imageService.findByPlaceId(placeId));
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        return ResponseEntity.ok(imageService.save(image));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image imageDetails) {
        return imageService.findById(id)
                .map(image -> {
                    image.setId(id);
                    image.setUrl(imageDetails.getUrl());
                    image.setDescription(imageDetails.getDescription());
                    Image updatedImage = imageService.save(image);
                    return ResponseEntity.ok(updatedImage);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

