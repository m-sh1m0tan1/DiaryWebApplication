package com.example.diarywebapplication.entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PasswordRecovery {
    private long id;
    private long userId;
    private String tokenHash;
    private OffsetDateTime expiresAt;
    private OffsetDateTime usedAt;
}
