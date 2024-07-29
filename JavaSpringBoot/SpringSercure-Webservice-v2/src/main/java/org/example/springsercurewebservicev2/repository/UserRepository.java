package org.example.springsercurewebservicev2.repository;

import org.example.springsercurewebservicev2.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
