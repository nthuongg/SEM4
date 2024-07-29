package org.example.travel.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.travel.Model.Comment;
import org.example.travel.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        Comment comment = commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/image/{imageId}")
    public ResponseEntity<List<Comment>> getCommentsByImageId(@PathVariable("imageId") Long imageId) {
        return ResponseEntity.ok(commentService.findByImageId(imageId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(commentService.findByUserId(userId));
    }

}
