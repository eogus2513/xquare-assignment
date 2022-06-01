package com.xquare.assignment.domain.client.global.domain;

import com.xquare.assignment.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Client extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String accountId;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private Role role;

    @Builder
    public Client(String accountId, String password, String name, String profileImageUrl, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }
}
