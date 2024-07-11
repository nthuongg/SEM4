package org.example.bookapiv21.repository;

import org.example.bookapiv21.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoty extends JpaRepository<Book, Integer> {
}
