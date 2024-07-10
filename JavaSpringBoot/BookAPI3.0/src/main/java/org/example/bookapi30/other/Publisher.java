//package org.example.bookapi30.other;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@Entity
//public class Publisher {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
//    private Set<BookPublisher> bookPublishers = new HashSet<>();
//
//    public Publisher(String name) {
//        this.name = name;
//    }
//
//    public Publisher() {
//
//    }
//}
