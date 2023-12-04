package com.hadam.hadam.repository;

import com.hadam.hadam.domain.Diary;
import com.hadam.hadam.domain.DiaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryInfoRepository extends JpaRepository<DiaryInfo, Long> {

    DiaryInfo findDiaryInfoByDiary(Diary diary);
}
