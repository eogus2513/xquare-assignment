package com.xquare.assignment.domain.post.controller;

import com.xquare.assignment.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;
}
