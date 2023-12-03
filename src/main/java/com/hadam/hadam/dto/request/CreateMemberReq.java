package com.hadam.hadam.dto.request;

public record CreateMemberReq(
        String identifier,
        String email,
        String nickName
) {
}
