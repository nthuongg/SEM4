package org.example.travel.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @CreationTimestamp //tự động set time thời điểm hiện tại (cách 1)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // tự động set time thời điểm hiện tại (cách 2)
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }

}