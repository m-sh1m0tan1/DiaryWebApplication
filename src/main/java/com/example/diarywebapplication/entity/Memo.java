package com.example.diarywebapplication.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Memo {
    private long id;
    private long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
