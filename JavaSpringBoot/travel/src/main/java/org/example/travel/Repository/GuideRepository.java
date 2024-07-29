package org.example.travel.Repository;

import org.example.travel.Model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    List<Guide> findByUserUsernameContainingIgnoreCase(String username);
}
