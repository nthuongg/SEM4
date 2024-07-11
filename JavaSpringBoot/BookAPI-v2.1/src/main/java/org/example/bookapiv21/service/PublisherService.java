package org.example.bookapiv21.service;

import org.example.bookapiv21.model.Publisher;
import org.example.bookapiv21.repository.PublisherRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepositoty publisherRepositoty;

    public List<Publisher> getAllPublishers() {
        return publisherRepositoty.findAll();
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepositoty.save(publisher);
    }

    public Optional<Publisher> getPublisherById(int id) {
        return publisherRepositoty.findById(id);
    }

    public void deletePublisher(int id) {
        publisherRepositoty.deleteById(id);
    }

    public Publisher updatePublisher(int id, Publisher publisher) {
        if (publisherRepositoty.existsById(id)) {
            publisher.setId(id);
            return publisherRepositoty.save(publisher);
        } else {
            throw new IllegalArgumentException("Publisher with id " + id + " does not exist");
        }
    }

}
