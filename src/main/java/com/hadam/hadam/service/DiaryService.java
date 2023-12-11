package com.hadam.hadam.service;

import com.hadam.hadam.domain.Diary;
import com.hadam.hadam.domain.DiaryInfo;
import com.hadam.hadam.domain.Member;
import com.hadam.hadam.dto.request.UpdateDiaryReq;
import com.hadam.hadam.dto.response.MonthlyListReq;
import com.hadam.hadam.dto.response.MonthlyRepresentRes;
import com.hadam.hadam.global.error.exception.EntityNotFoundException;
import com.hadam.hadam.global.error.exception.ErrorCode;
import com.hadam.hadam.repository.DiaryInfoRepository;
import com.hadam.hadam.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.Comparator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryInfoRepository diaryInfoRepository;

    public Long updateDiary(Long diaryId, UpdateDiaryReq updateDiaryReq){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diary.setDiaryContent(updateDiaryReq.content());
        return diaryId;
    }

    public void deleteDiary(Long diaryId){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diaryRepository.delete(diary);
        DiaryInfo diaryInfo = getDiaryInfoByDiary(diary);
        diaryInfoRepository.delete(diaryInfo);
    }

    public DiaryInfo getDiaryInfoByDiary(Diary diary) {
        return diaryInfoRepository.findDiaryInfoByDiary(diary);
    }

    public void updateIsLiked(Long diaryId){
        Diary diary = diaryRepository.findByIdOrThrow(diaryId);
        diary.setIsLiked(!diary.isLiked());
        diaryRepository.save(diary);
    }

    @Transactional(readOnly = true)
    public MonthlyRepresentRes getMonthlyDiary(Long memberId, int year, int month){
        List<Diary> diaries = diaryRepository.findDiariesByMemberIdAndYearMonth(
                memberId,
                year,
                month
        );

        if (diaries.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.MONTHLY_NOT_FOUND);
        }

        // 랜덤으로 선택
        Random random = new Random();
        Diary randomDiary = diaries.get(random.nextInt(diaries.size()));

        return new MonthlyRepresentRes(
                randomDiary.getId(),
                randomDiary.getImg(),
                formatYearMonth(year, month)
        );
    }

    public static String formatYearMonth(int year, int month) {
        return year + " " + month + "월";
    }

    @Transactional(readOnly = true)
    public List<MonthlyListReq> getMonthlyAllDiaryNew(Long memberId, int year, int month) {
        List<Diary> diaries = diaryRepository.findDiariesByMemberIdAndYearMonth(
                memberId,
                year,
                month
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");

        return diaries.stream()
                .map(diary -> new MonthlyListReq(
                        diary.getId(),
                        diary.getImg(),
                        truncateContent(diary.getContent()),  // truncateContent 메서드 사용
                        diary.getDate().format(formatter)
                ))
                .sorted(Comparator.comparing(MonthlyListReq::date).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonthlyListReq> getMonthlyAllDiaryOld(Long memberId, int year, int month) {
        List<Diary> diaries = diaryRepository.findDiariesByMemberIdAndYearMonth(
                memberId,
                year,
                month
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");

        return diaries.stream()
                .map(diary -> new MonthlyListReq(
                        diary.getId(),
                        diary.getImg(),
                        truncateContent(diary.getContent()),  // truncateContent 메서드 사용
                        diary.getDate().format(formatter)
                ))
                .sorted(Comparator.comparing(MonthlyListReq::date))
                .collect(Collectors.toList());
    }

    // TODO : 제목 넣을지에 따라 수정
    private String truncateContent(String content) {
        int maxLength = 15;
        if (content.length() > maxLength) {
            return content.substring(0, maxLength) + "...";
        }
        return content;
    }


}
