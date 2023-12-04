package com.hadam.hadam.service;

import com.hadam.hadam.domain.Diary;
import com.hadam.hadam.domain.DiaryInfo;
import com.hadam.hadam.domain.Member;
import com.hadam.hadam.dto.request.UpdateDiaryReq;
import com.hadam.hadam.repository.DiaryInfoRepository;
import com.hadam.hadam.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryInfoRepository diaryInfoRepository;

    @Transactional
    public Long updateDiary(Long diaryId, UpdateDiaryReq updateDiaryReq){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diary.setDiaryContent(updateDiaryReq.content());
        return diaryId;
    }

    @Transactional
    public void deleteDiary(Long diaryId){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diaryRepository.delete(diary);
        DiaryInfo diaryInfo = getDiaryInfoByDiary(diary);
        diaryInfoRepository.delete(diaryInfo);
    }

    public DiaryInfo getDiaryInfoByDiary(Diary diary) {
        return diaryInfoRepository.findDiaryInfoByDiary(diary);
    }

    @Transactional
    public void updateIsLiked(Long diaryId){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diary.setIsLiked(!diary.isLiked());
        diaryRepository.save(diary);
    }



}
