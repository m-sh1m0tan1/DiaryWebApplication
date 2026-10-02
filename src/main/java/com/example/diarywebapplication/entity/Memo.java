package com.example.diarywebapplication.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class Memo {
    private long id;
    private long userId;
    private String content;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private LocalDate weekStartDate;
}
