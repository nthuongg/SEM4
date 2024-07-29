package org.example.travel.Service;

import org.example.travel.Model.Guide;
import org.example.travel.Repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {
    @Autowired
    private GuideRepository guideRepository;

    public List<Guide> findAll() {
        return guideRepository.findAll();
    }

    public List<Guide> findByUsername(String username) {
        return guideRepository.findByUserUsernameContainingIgnoreCase(username);
    }

    public Guide save(Guide guide){
        return guideRepository.save(guide);
    }
}
