package org.example.pro5.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "publisher")

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name")
    private String name;

    @ManyToMany
    @JoinTable(name = "book_publishser", joinColumns =
    @JoinColumn(name = "book_id",referencedColumnName ="id"),
            inverseJoinColumns =@JoinColumn(name = "publisher_id",
                    referencedColumnName = "id"))
    private Set<Publisher> publishers;

    public Book(String name, Publisher... publishers) {
        this.name = name;
        this.publishers = Stream.of(publishers).collect(Collectors.toSet());
        this.publishers.forEach(x ->x.getBooks().add(this));
    }
}
