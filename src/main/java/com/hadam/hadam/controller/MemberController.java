package com.hadam.hadam.controller;

import com.hadam.hadam.dto.request.CreateMemberReq;
import com.hadam.hadam.global.common.BaseResponse;
import com.hadam.hadam.global.common.SuccessCode;
import com.hadam.hadam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/new")
    public ResponseEntity<BaseResponse<?>> createMember(@RequestBody CreateMemberReq createMemberReq){
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, memberService.createMember(createMemberReq)));
    }




}
