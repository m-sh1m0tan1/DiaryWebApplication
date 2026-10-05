package com.example.diarywebapplication.Form;

import com.example.diarywebapplication.entity.Mood;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DiaryForm {
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate diaryDate;
    private String title;
    private Mood currentMood;
    private String content;
    private String goodThings;
    private String tomorrowNote;
}
