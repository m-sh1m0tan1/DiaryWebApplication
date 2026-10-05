package com.example.diarywebapplication.viewmodel;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiaryWeekViewModel {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<DiaryViewModel> diaries;
}
