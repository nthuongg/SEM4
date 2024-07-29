package org.example.springsercurewebservicev2.service;

import org.example.springsercurewebservicev2.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    Publisher createPublisher(Publisher publisher);
    Optional<Publisher> getPublisherById(Long id);
    List<Publisher> getAllPublishers();
    void addBook(Long publisherId, Long bookId);
    void removeBook(Long publisherId, Long bookId);
    void removePublisher(Long publisherId);
}
