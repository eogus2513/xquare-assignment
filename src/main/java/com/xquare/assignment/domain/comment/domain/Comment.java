package com.xquare.assignment.domain.comment.domain;

import com.xquare.assignment.domain.auth.common.domain.Auth;
import com.xquare.assignment.domain.post.domain.Post;
import com.xquare.assignment.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String comment;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id", nullable = false)
    private Auth auth;

    @Builder
    public Comment(String comment, LocalDateTime updatedAt, Post post, Auth auth) {
        this.comment = comment;
        this.updatedAt = updatedAt;
        this.post = post;
        this.auth = auth;
    }

    public void updateContent(String comment) {
        this.comment = comment;
    }

    public Long getClientId() {
        return this.auth.getId();
    }

    public String getClientName() {
        return this.auth.getName();
    }

    public String getClientProfileImageUrl() {
        return this.auth.getProfileImageUrl();
    }
}
