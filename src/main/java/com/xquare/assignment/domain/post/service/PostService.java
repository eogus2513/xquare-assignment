package com.xquare.assignment.domain.post.service;

import com.xquare.assignment.domain.auth.common.domain.Client;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.post.controller.dto.request.CreatePostRequest;
import com.xquare.assignment.domain.post.controller.dto.request.UpdatePostRequest;
import com.xquare.assignment.domain.post.controller.dto.response.PostListResponse;
import com.xquare.assignment.domain.post.controller.dto.response.PostListResponse.ClientResponse;
import com.xquare.assignment.domain.post.controller.dto.response.PostListResponse.PostResponse;
import com.xquare.assignment.domain.post.domain.Post;
import com.xquare.assignment.domain.post.domain.repository.PostRepository;
import com.xquare.assignment.domain.post.exception.NoPermissionToDeletePostException;
import com.xquare.assignment.domain.post.exception.NoPermissionToModifyPostException;
import com.xquare.assignment.domain.post.exception.PostNotFoundException;
import com.xquare.assignment.global.facade.CurrentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CurrentFacade currentFacade;

    @Transactional(readOnly = true)
    public PostListResponse getPostList(Pageable pageable) {
        List<PostResponse> postList = postRepository.findAllByJoinFetch(pageable)
                .stream()
                .map(this::buildPostList)
                .collect(Collectors.toList());

        return new PostListResponse(postList);
    }

    private PostResponse buildPostList(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .client(ClientResponse.builder()
                        .clientId(post.getClientId())
                        .name(post.getClient().getName())
                        .profileImageUrl(post.getClient().getProfileImageUrl())
                        .build())
                .build();
    }

    @Transactional
    public void createPost(CreatePostRequest request) {
        Client client = currentFacade.getCurrentClient();

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .client(client)
                .build();
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(UpdatePostRequest request) {
        Client client = currentFacade.getCurrentClient();

        Post post = getPost(request.getPostId());

        if (!post.getClientId().equals(client.getId())) {
            throw NoPermissionToModifyPostException.EXCEPTION;
        }

        post.updateTitleAndContent(request);
    }

    @Transactional
    public void deletePost(Long postId) {
        Client client = currentFacade.getCurrentClient();

        Post post = getPost(postId);

        if (Role.USER.equals(client.getRole()) && !post.getClientId().equals(client.getId())) {
            throw NoPermissionToDeletePostException.EXCEPTION;
        }

        postRepository.delete(post);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }
}
