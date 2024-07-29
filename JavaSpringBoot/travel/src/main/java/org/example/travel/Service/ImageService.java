package org.example.travel.Service;

import org.example.travel.Model.Image;
import org.example.travel.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findByPlaceId(Long placeId) {
        return imageRepository.findByPlaceId(placeId);
    }


    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }
}
