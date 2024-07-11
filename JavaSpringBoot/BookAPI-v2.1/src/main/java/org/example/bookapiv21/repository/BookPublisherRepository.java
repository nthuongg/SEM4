package org.example.bookapiv21.repository;

import org.example.bookapiv21.model.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPublisherRepository extends JpaRepository<BookPublisher, Integer> {
}
