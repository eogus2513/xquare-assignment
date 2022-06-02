package com.xquare.assignment.domain.post.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostListResponse {

    private final List<PostResponse> postList;

    @Getter
    @Builder
    public static class PostResponse {
        private final Long postId;
        private final String title;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final Long clientId;
    }
}
