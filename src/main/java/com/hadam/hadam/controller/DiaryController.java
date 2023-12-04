package com.hadam.hadam.controller;

import com.hadam.hadam.dto.request.UpdateDiaryReq;
import com.hadam.hadam.global.common.BaseResponse;
import com.hadam.hadam.global.common.SuccessCode;
import com.hadam.hadam.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PatchMapping("/{diaryId}")
    public ResponseEntity<BaseResponse<?>> updateDiaryContent(@PathVariable Long diaryId, @RequestBody UpdateDiaryReq updateDiaryReq){
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, diaryService.updateDiary(diaryId, updateDiaryReq)));
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<BaseResponse> deleteDiary(@PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, "일기 삭제에 성공하였습니다."));
    }




}
