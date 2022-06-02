package com.xquare.assignment.domain.comment.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentListResponse {

    private final List<CommentResponse> commentList;

    @Getter
    @Builder
    public static class CommentResponse {
        private final Long commentId;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final ClientResponse client;
    }

    @Getter
    @Builder
    public static class ClientResponse {
        private final Long clientId;
        private final String name;
        private final String profileImageUrl;
    }
}
