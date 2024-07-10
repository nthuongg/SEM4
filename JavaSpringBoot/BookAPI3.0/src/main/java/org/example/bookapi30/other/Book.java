//package org.example.bookapi30.other;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.Set;
//
//@Data
//@Entity
//public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "bookName")
//    private String name;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private Set<BookPublisher> bookPublishers;
//}
