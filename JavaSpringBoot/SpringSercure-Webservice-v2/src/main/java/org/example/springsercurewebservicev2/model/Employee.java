package org.example.springsercurewebservicev2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position;

    @OneToOne
    private AppUser appUser; // Link with AppUser for login purposes
}
