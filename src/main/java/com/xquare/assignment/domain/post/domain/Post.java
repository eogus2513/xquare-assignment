package com.xquare.assignment.domain.post.domain;

import com.xquare.assignment.domain.client.global.domain.Client;
import com.xquare.assignment.domain.post.controller.dto.request.UpdatePostRequest;
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
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Builder
    public Post(String title, String content, LocalDateTime updatedAt, Client client) {
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
        this.client = client;
    }

    public Long getClientId() {
        return this.client.getId();
    }

    public void updateTitleAndContent(UpdatePostRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
