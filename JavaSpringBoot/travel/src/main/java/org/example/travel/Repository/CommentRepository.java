package org.example.travel.Repository;

import org.example.travel.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findByImageId(Long imageId);
    List<Comment> findByUserId(Long userId);
}
