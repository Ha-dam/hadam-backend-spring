package com.hadam.hadam.repository;

import com.hadam.hadam.domain.Diary;
import com.hadam.hadam.global.error.exception.EntityNotFoundException;
import com.hadam.hadam.global.error.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    default Diary findByIdOrThrow(Long diaryId){
        return findById(diaryId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }
}
