package com.example.diarywebapplication.viewmodel;

import com.example.diarywebapplication.entity.Mood;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryViewModel {
    private long id;
    private String title;
    private Mood currentMood;
    private String content;
    private String goodThings;
    private String tomorrowNote;
    private LocalDate diaryDate;

    public String getDisplayTitle() {
        final int MAX_DISPLAY_LENGTH = 18;
        return abbreviate(title, MAX_DISPLAY_LENGTH);
    }

    public String getDisplayContent() {
        final int MAX_DISPLAY_LENGTH = 80;
        return abbreviate(content, MAX_DISPLAY_LENGTH);
    }

    public String getDisplayTomorrowNote() {
        final int MAX_DISPLAY_LENGTH = 30;
       if (tomorrowNote == null || tomorrowNote.isBlank()) {
           return "-";
       }
        return abbreviate(tomorrowNote, MAX_DISPLAY_LENGTH);
    }

    public boolean isTitlePresent() {
        return title != null && !title.isBlank();
    }

    private static String abbreviate(String text, int limit) {
        if (text == null) {
            return "";
        }
        if (text.codePointCount(0, text.length()) <= limit) {
            return text;
        }
        int endIndex = text.offsetByCodePoints(0, limit);
        return text.substring(0, endIndex) + "...";
    }
}
