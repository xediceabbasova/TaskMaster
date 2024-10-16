package com.khadija.taskmaster.repository;

import com.khadija.taskmaster.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
