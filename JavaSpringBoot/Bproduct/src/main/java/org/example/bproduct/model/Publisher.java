package org.example.bproduct.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<BookPublisher> bookPublishers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookPublisher> getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(Set<BookPublisher> bookPublishers) {
        this.bookPublishers = bookPublishers;
    }
}
