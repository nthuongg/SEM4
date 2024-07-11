package org.example.bookapiv21.repository;

import org.example.bookapiv21.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepositoty extends JpaRepository<Publisher, Integer> {
}
