//package org.example.bookapi30.other;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//public class BookPublisher {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "book_id")
//    private Book book;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "publisher_id")
//    private Publisher publisher;
//
//    private Date publishedDate;
//}
//
