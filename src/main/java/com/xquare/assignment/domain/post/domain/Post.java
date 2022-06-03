package com.xquare.assignment.domain.post.domain;

import com.xquare.assignment.domain.auth.common.domain.Auth;
import com.xquare.assignment.domain.comment.domain.Comment;
import com.xquare.assignment.domain.post.controller.dto.request.UpdatePostRequest;
import com.xquare.assignment.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id", nullable = false)
    private Auth auth;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    public Post(String title, String content, LocalDateTime updatedAt, Auth auth) {
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
        this.auth = auth;
    }

    public Long getClientId() {
        return this.auth.getId();
    }

    public void updateTitleAndContent(UpdatePostRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
