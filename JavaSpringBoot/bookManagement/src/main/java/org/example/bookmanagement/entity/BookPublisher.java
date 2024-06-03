package org.example.bookmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BookPublisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime publishedDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
}
