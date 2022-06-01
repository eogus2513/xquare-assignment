package com.xquare.assignment.domain.post.service;

import com.xquare.assignment.domain.client.global.domain.Client;
import com.xquare.assignment.domain.post.controller.dto.request.CreatePostRequest;
import com.xquare.assignment.domain.post.domain.Post;
import com.xquare.assignment.domain.post.domain.repository.PostRepository;
import com.xquare.assignment.global.facade.CurrentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CurrentFacade currentFacade;

    public void createPost(CreatePostRequest request) {
        Client client = currentFacade.getCurrentClient();

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .client(client)
                .build();
        postRepository.save(post);
    }
}
