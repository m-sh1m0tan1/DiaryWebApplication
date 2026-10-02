package com.example.diarywebapplication.repository;

import com.example.diarywebapplication.entity.Diary;
import com.example.diarywebapplication.mapper.DiaryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public Diary getDiaryById(Long diaryId) {
        return diaryMapper.getDiaryById(diaryId);
    }

    public void insertDiary(Diary diary) {
        diaryMapper.insertDiary(diary);
    }

    @Transactional
    public Diary updateDiary(Diary diary) {
        int sqlResult = diaryMapper.updateDiary(diary);
        if (sqlResult < 1) {
            return null;
        }
        return diaryMapper.getDiaryById(diary.getId());
    }

    @Transactional
    public Diary completeDiary(Diary diary) {
        int sqlResult =  diaryMapper.completeDiary(diary);
        if (sqlResult < 1) {
            return null;
        }
        return diaryMapper.getDiaryById(diary.getId());
    }

}
