package com.hadam.hadam.domain;

import com.hadam.hadam.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String identifier;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private MemberStatus memberStatus;

}
