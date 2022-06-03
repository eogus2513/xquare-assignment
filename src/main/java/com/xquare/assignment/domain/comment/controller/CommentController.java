package com.xquare.assignment.domain.comment.controller;

import com.xquare.assignment.domain.comment.controller.dto.request.CreateCommentRequest;
import com.xquare.assignment.domain.comment.controller.dto.request.UpdateCommentRequest;
import com.xquare.assignment.domain.comment.controller.dto.response.CommentListResponse;
import com.xquare.assignment.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{post-id}")
    public CommentListResponse getCommentList(@PathVariable("post-id") Long postId) {
        return commentService.getCommentList(postId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{post-id}")
    public void createComment(@PathVariable("post-id") Long postId,
                              @RequestBody @Valid CreateCommentRequest request) {
        commentService.createComment(postId, request.getComment());
    }

    @PatchMapping("/{comment-id}")
    public void updateComment(@PathVariable("comment-id") Long commentId,
                              @RequestBody @Valid UpdateCommentRequest request) {
        commentService.updateComment(commentId, request.getComment());
    }

    @DeleteMapping("/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long commentId) {
        commentService.deleteComment(commentId);
    }
}
