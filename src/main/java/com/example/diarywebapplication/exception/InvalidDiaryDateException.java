package com.example.diarywebapplication.exception;

public class InvalidDiaryDateException extends RuntimeException {
    public InvalidDiaryDateException(String message) {
        super(message);
    }
}
