package org.example.bookapi22;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Entity
@Data
// @NoArgsConstructor
@Getter
@Setter
@Value
@Slf4j  //log check các lỗi xảy ra với thực thể
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false)
    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<Publisher> publishers;


}
