package com.example.diarywebapplication.exception.handler;

import com.example.diarywebapplication.exception.InvalidDiaryDateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class InvalidDiaryDateExceptionHandler {
    @ExceptionHandler(InvalidDiaryDateException.class)
    public String handleInvalidDiaryDateException(InvalidDiaryDateException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/diary";
    }
}
