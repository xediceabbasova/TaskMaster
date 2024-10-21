package com.khadija.taskmaster.service;

import com.khadija.taskmaster.dto.CommentDto;
import com.khadija.taskmaster.dto.CommentMapper;
import com.khadija.taskmaster.dto.request.CommentRequest;
import com.khadija.taskmaster.exception.CommentNotFoundException;
import com.khadija.taskmaster.model.Comment;
import com.khadija.taskmaster.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final TaskService taskService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, TaskService taskService, UserService userService) {
        this.commentRepository = commentRepository;
        this.mapper = commentMapper;
        this.taskService = taskService;
        this.userService = userService;
    }

    public CommentDto createComment(final Long taskId, final CommentRequest request) {
        Comment comment = mapper.toComment(request, taskService.findTaskById(taskId), userService.getCurrentUser());
        return mapper.fromComment(commentRepository.save(comment));
    }

    public CommentDto getCommentById(final Long commentId) {
        return mapper.fromComment(findCommentById(commentId));
    }

    public List<CommentDto> getCommentsByTaskId(final Long taskId) {
        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(mapper::fromComment)
                .toList();
    }

    public CommentDto updateComment(final Long commentId, final CommentRequest request) {
        Comment comment = findCommentById(commentId);
        Comment updatedComment = new Comment(
                comment.getId(),
                Optional.ofNullable(request.content()).orElse(comment.getContent()),
                comment.getCreatedDate(),
                comment.getTask(),
                comment.getAuthor()
        );
        return mapper.fromComment(commentRepository.save(updatedComment));
    }

    public void deleteComment(final Long commentId) {
        findCommentById(commentId);
        commentRepository.deleteById(commentId);
    }

    private Comment findCommentById(final Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
    }

}
