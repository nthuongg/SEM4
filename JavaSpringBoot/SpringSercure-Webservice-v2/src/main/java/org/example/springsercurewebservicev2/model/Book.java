package org.example.springsercurewebservicev2.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher",joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<Publisher> publishers ;   // quan he nhieu nhieu dung set<...>
}