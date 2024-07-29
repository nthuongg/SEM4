package org.example.springsercurewebservicev2.repository;

import org.example.springsercurewebservicev2.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
