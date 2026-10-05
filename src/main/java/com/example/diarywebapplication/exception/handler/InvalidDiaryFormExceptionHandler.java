package com.example.diarywebapplication.exception.handler;

import com.example.diarywebapplication.exception.InvalidDiaryFormException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class InvalidDiaryFormExceptionHandler {
    @ExceptionHandler(InvalidDiaryFormException.class)
    public String handleInvalidDiaryFormException(InvalidDiaryFormException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/diary";
    }
}
