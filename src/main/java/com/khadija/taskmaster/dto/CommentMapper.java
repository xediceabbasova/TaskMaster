package com.khadija.taskmaster.dto;

import com.khadija.taskmaster.dto.request.CommentRequest;
import com.khadija.taskmaster.model.Comment;
import com.khadija.taskmaster.model.Task;
import com.khadija.taskmaster.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toComment(CommentRequest request, Task task, User user) {
        return new Comment(
                request.content(),
                task,
                user
        );
    }

    public CommentDto fromComment(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedDate(),
                comment.getAuthor().getUsername()
        );
    }
}
