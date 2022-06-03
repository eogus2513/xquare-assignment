package com.xquare.assignment.domain.comment.service;

import com.xquare.assignment.domain.auth.common.domain.Auth;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.comment.controller.dto.response.CommentListResponse;
import com.xquare.assignment.domain.comment.controller.dto.response.CommentListResponse.ClientResponse;
import com.xquare.assignment.domain.comment.controller.dto.response.CommentListResponse.CommentResponse;
import com.xquare.assignment.domain.comment.domain.Comment;
import com.xquare.assignment.domain.comment.domain.repository.CommentRepository;
import com.xquare.assignment.domain.comment.exception.CommentNotFoundException;
import com.xquare.assignment.domain.comment.exception.NoPermissionToDeleteCommentException;
import com.xquare.assignment.domain.comment.exception.NoPermissionToModifyCommentException;
import com.xquare.assignment.domain.post.domain.Post;
import com.xquare.assignment.domain.post.domain.repository.PostRepository;
import com.xquare.assignment.domain.post.exception.PostNotFoundException;
import com.xquare.assignment.global.facade.CurrentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CurrentFacade currentFacade;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public CommentListResponse getCommentList(Long postId) {
        Post post = getPost(postId);

        List<CommentResponse> commentList = commentRepository.findAllByJoinFetch(post.getId())
                .stream()
                .map(this::buildCommentList)
                .collect(Collectors.toList());

        return new CommentListResponse(commentList);
    }

    private CommentResponse buildCommentList(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .client(ClientResponse.builder()
                        .clientId(comment.getClientId())
                        .name(comment.getClientName())
                        .profileImageUrl(comment.getClientProfileImageUrl())
                        .build())
                .build();
    }

    @Transactional
    public void createComment(Long postId, String comment) {
        Auth auth = currentFacade.getCurrentAuth();

        Post post = getPost(postId);

        commentRepository.save(Comment.builder()
                .comment(comment)
                .post(post)
                .auth(auth)
                .build());
    }

    @Transactional
    public void updateComment(Long commentId, String content) {
        Auth auth = currentFacade.getCurrentAuth();

        Comment comment = getComment(commentId);

        if (!comment.getAuth().equals(auth)) {
            throw NoPermissionToModifyCommentException.EXCEPTION;
        }

        comment.updateContent(content);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Auth auth = currentFacade.getCurrentAuth();

        Comment comment = getComment(commentId);

        if (Role.USER.equals(auth.getRole()) && !comment.getAuth().equals(auth)) {
            throw NoPermissionToDeleteCommentException.EXCEPTION;
        }

        commentRepository.delete(comment);
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }
}
