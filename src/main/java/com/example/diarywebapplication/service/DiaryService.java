package com.example.diarywebapplication.service;

import com.example.diarywebapplication.Form.DiaryForm;
import com.example.diarywebapplication.entity.Diary;
import com.example.diarywebapplication.exception.DiaryNotFoundException;
import com.example.diarywebapplication.exception.InvalidDiaryDateException;
import com.example.diarywebapplication.repository.DiaryRepository;
import com.example.diarywebapplication.viewmodel.DiaryViewModel;
import com.example.diarywebapplication.viewmodel.DiaryWeekViewModel;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public DiaryWeekViewModel getThisWeekDiariesByUserId(long userId, LocalDate nowDate) {
        LocalDate startDate = nowDate.getDayOfWeek() == DayOfWeek.SUNDAY ? nowDate : nowDate.minusDays(nowDate.getDayOfWeek().getValue());
        LocalDate endDate = startDate.plusDays(7);
        List<Diary> savedDiaries = diaryRepository.getThisWeekDiariesByUserId(userId, startDate, endDate);
        ArrayList<DiaryViewModel> diaryViewModels = new ArrayList<>();
        for (Diary savedDiary : savedDiaries) {
            DiaryViewModel diaryViewModel = createDiaryViewModel(savedDiary);
            diaryViewModels.add(diaryViewModel);
        }
        DiaryWeekViewModel diaryWeekViewModel = new DiaryWeekViewModel();
        diaryWeekViewModel.setStartDate(startDate);
        diaryWeekViewModel.setEndDate(startDate.plusDays(6));
        diaryWeekViewModel.setDiaries(diaryViewModels);
        return diaryWeekViewModel;
    }

    private static DiaryViewModel createDiaryViewModel(Diary savedDiary) {
        DiaryViewModel diaryViewModel = new DiaryViewModel();
        diaryViewModel.setId(savedDiary.getId());
        diaryViewModel.setTitle(savedDiary.getTitle() == null ? "" : savedDiary.getTitle());
        diaryViewModel.setCurrentMood(savedDiary.getCurrentMood());
        diaryViewModel.setGoodThings(savedDiary.getGoodThings() == null ? "" : savedDiary.getGoodThings());
        diaryViewModel.setTomorrowNote(savedDiary.getTomorrowNote() == null ? "" : savedDiary.getTomorrowNote());
        diaryViewModel.setContent(savedDiary.getContent());
        diaryViewModel.setDiaryDate(savedDiary.getDiaryDate());
        return diaryViewModel;
    }

    public boolean insertDiary(long userId, DiaryForm diaryForm) {
        Diary diary = new Diary();
        LocalDate diaryDate = diaryForm.getDiaryDate();
        if (diaryDate == null) {
            throw new InvalidDiaryDateException("日付が選択されていません");
        } else if (diaryDate.isAfter(LocalDate.now())) {
            throw new InvalidDiaryDateException("未来の日付は作成できません");
        }
        diary.setTitle(diaryForm.getTitle() == null || diaryForm.getTitle().isBlank() ? null : diaryForm.getTitle());
        diary.setCurrentMood(diaryForm.getCurrentMood() == null ? null : diaryForm.getCurrentMood());
        diary.setContent(diaryForm.getContent() == null || diaryForm.getContent().isBlank() ? null : diaryForm.getContent());
        diary.setGoodThings(diaryForm.getGoodThings() == null || diaryForm.getGoodThings().isBlank() ? null : diaryForm.getGoodThings());
        diary.setTomorrowNote(diaryForm.getTomorrowNote() == null || diaryForm.getTomorrowNote().isBlank() ? null : diaryForm.getTomorrowNote());

        diary.setUserId(userId);
        diary.setDiaryDate(diaryForm.getDiaryDate());

        return diaryRepository.insertDiary(diary) == 1;
    }

    public Diary getDiaryByDiaryId(long diaryId, long userId) {
        Diary diary = diaryRepository.getDiaryById(diaryId);
        if (diary == null) {
            throw new DiaryNotFoundException("指定された日記が見つかりません");
        }
        if (diary.getUserId() != userId) {
            throw new DiaryNotFoundException("指定された日記が見つかりません");
        }
        return diary;
    }

    public Diary updateDiary(DiaryForm diaryForm, long userId, long diaryId) {
        Diary diary = getDiaryByDiaryId(diaryId, userId);
        if (diary == null) {
            throw new DiaryNotFoundException("指定された日記が見つかりません");
        }

        diary.setUserId(userId);
        diary.setTitle(diaryForm.getTitle() == null || diaryForm.getTitle().isBlank() ? null : diaryForm.getTitle());
        diary.setCurrentMood(diaryForm.getCurrentMood() == null ? null : diaryForm.getCurrentMood());
        diary.setContent(diaryForm.getContent() == null || diaryForm.getContent().isBlank() ? null : diaryForm.getContent());
        diary.setGoodThings(diaryForm.getGoodThings() == null || diaryForm.getGoodThings().isBlank() ? null : diaryForm.getGoodThings());
        diary.setTomorrowNote(diaryForm.getTomorrowNote() == null || diaryForm.getTomorrowNote().isBlank() ? null : diaryForm.getTomorrowNote());
        diary.setId(diaryId);
//        return diaryRepository.updateDiary(diary);
        Diary updatedDiary = diaryRepository.updateDiary(diary);
        if (updatedDiary == null) {
            throw new DiaryNotFoundException("指定された日記が見つかりません");
        }
        return updatedDiary;
    }
}


