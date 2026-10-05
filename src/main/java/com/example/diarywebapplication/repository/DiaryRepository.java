package com.example.diarywebapplication.repository;

import com.example.diarywebapplication.entity.Diary;
import com.example.diarywebapplication.mapper.DiaryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DiaryRepository {
    private final DiaryMapper diaryMapper;

    public DiaryRepository(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    public List<Diary> getDiariesByUserId(Long userId) {
        return diaryMapper.getDiariesByUserId(userId);
    }

    public List<Diary> getThisWeekDiariesByUserId(Long userId, LocalDate startDate, LocalDate endDate) {
        return diaryMapper.getThisWeekDiariesByUserId(userId, startDate, endDate);
    }

    public Diary getDiaryById(Long diaryId) {
        return diaryMapper.getDiaryById(diaryId);
    }

    public int insertDiary(Diary diary) {
        return diaryMapper.insertDiary(diary);
    }

    @Transactional
    public Diary updateDiary(Diary diary) {
        int sqlResult = diaryMapper.updateDiary(diary);
        if (sqlResult < 1) {
            return null;
        }
        return diaryMapper.getDiaryById(diary.getId());
    }
}
