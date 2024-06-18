package org.example.exam.repository;

import org.example.exam.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByMemberUserId(String userId);
}