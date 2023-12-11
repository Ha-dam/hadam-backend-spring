package com.hadam.hadam.domain;

import com.hadam.hadam.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary")
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @Column(columnDefinition = "LONGTEXT")
    private String img;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setDiaryContent(String content){
        this.content = content;
    }

    public void setIsLiked(boolean isLiked){
        this.isLiked = isLiked;
    }

}
