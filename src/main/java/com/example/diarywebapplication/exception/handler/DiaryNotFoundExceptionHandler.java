package com.example.diarywebapplication.exception.handler;

import com.example.diarywebapplication.exception.DiaryNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class DiaryNotFoundExceptionHandler {
    @ExceptionHandler(DiaryNotFoundException.class)
    public String handleDiaryNotFoundException(DiaryNotFoundException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/diary";
    }
}
