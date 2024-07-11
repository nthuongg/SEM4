package org.example.bookapiv21.controller;

import org.example.bookapiv21.model.Publisher;
import org.example.bookapiv21.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2.1/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @PostMapping
    public Publisher createPublisher(@RequestBody Publisher publisher){
        return publisherService.createPublisher(publisher);
    }


}
