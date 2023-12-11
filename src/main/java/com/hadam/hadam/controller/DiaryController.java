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
    public ResponseEntity<BaseResponse<?>> deleteDiary(@PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK));
    }

    @PatchMapping("/{diaryId}/likes")
    public ResponseEntity<BaseResponse<?>> updateIsLiked(@PathVariable Long diaryId){
        diaryService.updateIsLiked(diaryId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK));
    }

    @GetMapping("/monthly")
    public ResponseEntity<BaseResponse<?>> getMonthlyDiary(@RequestParam Long memberId, @RequestParam int year, @RequestParam int month){
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, diaryService.getMonthlyDiary(memberId, year, month)));
    }

    @GetMapping("/monthly/new")
    public ResponseEntity<BaseResponse<?>> getMonthlyAllDiaryNew(@RequestParam Long memberId, @RequestParam int year, @RequestParam int month){
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, diaryService.getMonthlyAllDiaryNew(memberId, year, month)));
    }

    @GetMapping("/monthly/old")
    public ResponseEntity<BaseResponse<?>> getMonthlyAllDiaryOld(@RequestParam Long memberId, @RequestParam int year, @RequestParam int month){
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, diaryService.getMonthlyAllDiaryOld(memberId, year, month)));
    }

    @GetMapping("/detail/{diaryId}")
    public ResponseEntity<BaseResponse<?>> detailDiary(@PathVariable Long diaryId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, diaryService.getDetailDiary(diaryId)));
    }

}
