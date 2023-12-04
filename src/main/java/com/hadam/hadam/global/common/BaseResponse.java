package com.hadam.hadam.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class BaseResponse<T> {
    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> BaseResponse<?> of(SuccessCode successCode, T data) {
        return BaseResponse.builder()
                .status(successCode.getHttpStatus().value())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<?> of(SuccessCode successCode) {
        return BaseResponse.builder()
                .status(successCode.getHttpStatus().value())
                .message(successCode.getMessage())
                .build();
    }
}