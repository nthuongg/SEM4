package com.example.pro5.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "publishers")

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name")
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST) // Add CascadeType.PERSIST
    @JoinTable(
            name = "book_publishser",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private Set<Publisher> publishers = new HashSet<>();

    public Book(String name, Publisher... publishers) {
        this.name = name;
        for (Publisher publisher : publishers) {
            this.publishers.add(publisher);
            publisher.getBooks().add(this); // Add the book to the publisher's books
        }
    }

    public Book() {}
}