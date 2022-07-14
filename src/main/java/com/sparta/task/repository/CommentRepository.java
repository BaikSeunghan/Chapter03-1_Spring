package com.sparta.task.repository;

import com.sparta.task.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //    List<Comment> findAllByIdOrderByModifiedAtDesc();
    List<Comment> findAllByBlogIdOrderByCreatedAtDesc(Long blogId);
}
