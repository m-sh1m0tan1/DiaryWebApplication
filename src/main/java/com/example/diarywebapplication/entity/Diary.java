package com.example.diarywebapplication.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class Diary {
    private long id;
    private long userId;
    private String title;
    private Mood currentMood;
    private String content;
    private String goodThings;
    private String tomorrowNote;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private LocalDate diaryDate;
}
