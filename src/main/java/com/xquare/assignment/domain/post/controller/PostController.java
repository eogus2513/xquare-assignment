package com.xquare.assignment.domain.post.controller;

import com.xquare.assignment.domain.post.controller.dto.request.CreatePostRequest;
import com.xquare.assignment.domain.post.controller.dto.request.UpdatePostRequest;
import com.xquare.assignment.domain.post.controller.dto.response.PostListResponse;
import com.xquare.assignment.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public PostListResponse getPostList(Pageable pageable) {
        return postService.getPostList(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createPost(@RequestBody @Valid CreatePostRequest request) {
        postService.createPost(request);
    }

    @PatchMapping("/{post-id}")
    public void updatePost(@PathVariable("post-id") Long postId,
                           @RequestBody @Valid UpdatePostRequest request) {
        postService.updatePost(postId, request);
    }

    @DeleteMapping("/{post-id}")
    public void deletePost(@PathVariable("post-id") Long postId) {
        postService.deletePost(postId);
    }
}
