package com.hadam.hadam.repository;

import com.hadam.hadam.domain.Diary;
import com.hadam.hadam.global.error.exception.EntityNotFoundException;
import com.hadam.hadam.global.error.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    default Diary findByIdOrThrow(Long diaryId){
        return findById(diaryId)
                .orElseThrow(()-> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Query("SELECT d FROM Diary d " +
            "WHERE d.member.id = :memberId " +
            "AND YEAR(d.date) = :year AND MONTH(d.date) = :month")
    List<Diary> findDiariesByMemberIdAndYearMonth(@Param("memberId") Long memberId,
                                                  @Param("year") int year,
                                                  @Param("month") int month);

}
