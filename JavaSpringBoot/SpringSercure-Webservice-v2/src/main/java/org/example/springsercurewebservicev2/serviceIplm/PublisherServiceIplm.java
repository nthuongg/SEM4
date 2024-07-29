package org.example.springsercurewebservicev2.serviceIplm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.springsercurewebservicev2.model.Publisher;
import org.example.springsercurewebservicev2.repository.PublisherRepository;
import org.example.springsercurewebservicev2.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceIplm implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public void addBook(Long publisherId, Long bookId) {

    }

    @Override
    public void removeBook(Long publisherId, Long bookId) {

    }

    @Override
    public void removePublisher(Long publisherId) {

    }
}
