package com.khadija.taskmaster.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long commentId) {
        super(String.format("Comment with ID %d not found", commentId));
    }
}
