package com.hadam.hadam.service;

import com.hadam.hadam.domain.Diary;
import com.hadam.hadam.domain.Member;
import com.hadam.hadam.dto.request.UpdateDiaryReq;
import com.hadam.hadam.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Transactional
    public Long updateDiary(Long diaryId, UpdateDiaryReq updateDiaryReq){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diary.setDiaryContent(updateDiaryReq.content());
        return diaryId;
    }

}
