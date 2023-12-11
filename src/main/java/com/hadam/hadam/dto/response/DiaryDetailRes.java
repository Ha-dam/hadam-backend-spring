package com.hadam.hadam.dto.response;

public record DiaryDetailRes(
        Long id,
        String img,
        String title,
        String date,
        String content,
        boolean liked

) {
}
