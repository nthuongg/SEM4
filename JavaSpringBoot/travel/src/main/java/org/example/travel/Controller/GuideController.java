package org.example.travel.Controller;

import org.example.travel.Model.Guide;
import org.example.travel.Service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1.1/guides")
public class GuideController {
    @Autowired
    private GuideService guideService;

    @GetMapping
    public ResponseEntity<List<Guide>> getAllGuides() {
        return ResponseEntity.ok(guideService.findAll());
    }

    @PostMapping
    public ResponseEntity<Guide> createGuide(@RequestBody Guide guide) {
        return ResponseEntity.ok(guideService.save(guide));
    }


}
