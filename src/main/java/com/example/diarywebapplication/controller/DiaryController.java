package com.example.diarywebapplication.controller;

import com.example.diarywebapplication.Form.DiaryForm;
import com.example.diarywebapplication.entity.Diary;
import com.example.diarywebapplication.exception.InvalidDiaryDateException;
import com.example.diarywebapplication.security.CustomUserDetails;
import com.example.diarywebapplication.service.DiaryService;

import com.example.diarywebapplication.viewmodel.DiaryWeekViewModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@RequestMapping("/diary")
@Controller
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public String getHomePage(Model model,
                              @AuthenticationPrincipal CustomUserDetails userDetails,
                              @RequestParam(name = "diaryDate", required = false)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                              LocalDate diaryDate) {
        LocalDate targetDate = diaryDate != null ? diaryDate : LocalDate.now();
        DiaryWeekViewModel week = diaryService.getThisWeekDiariesByUserId(userDetails.getUserId(), targetDate);
        model.addAttribute("startDate", week.getStartDate());
        model.addAttribute("endDate", week.getEndDate());
        model.addAttribute("diaries", week.getDiaries());
        return "diary/home";
    }

    @GetMapping("/new")
    public String getNewDiaryPage(Model model,
                                  @RequestParam(name = "diaryDate", required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate diaryDate) {
        DiaryForm diaryForm = new DiaryForm();
        if (diaryDate != null && diaryDate.isAfter(LocalDate.now())) {
            throw new InvalidDiaryDateException("未来の日付は作成できません");
        }
        diaryForm.setDiaryDate(diaryDate != null ? diaryDate : LocalDate.now());
        model.addAttribute("diaryForm", diaryForm);
        model.addAttribute("dateEditable", diaryDate == null);
        return "diary/create";
    }

    @PostMapping("/new")
    public String postNewDiary(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute("diaryForm") DiaryForm diaryForm,
            BindingResult bindingResult,
            @RequestParam(name = "dateEditable", defaultValue = "false")
            boolean dateEditable,
            Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("dateEditable", dateEditable);
        if (diaryForm.getCurrentMood() == null) {
            bindingResult.rejectValue("currentMood", "required", "気分を選択してください。");
            return "diary/create";
        }
        if (diaryForm.getContent() == null || diaryForm.getContent().isBlank()) {
            bindingResult.rejectValue("content", "required", "内容を入力してください。");
            return "diary/create";
        }
        if (bindingResult.hasErrors()) {
            return "diary/create";
        }

        try {
            if (!diaryService.insertDiary(userDetails.getUserId(), diaryForm)) {
                bindingResult.rejectValue("diaryDate", "duplicate", "この日の日記は既に登録されています。");
                return "diary/create";
            }
        } catch (InvalidDiaryDateException e) {
            bindingResult.rejectValue("diaryDate", "invalidDiaryDate", e.getMessage());
            return "diary/create";
        }
        redirectAttributes.addFlashAttribute("successMessage", "日記を登録しました。(" + diaryForm.getDiaryDate() + ")");
        return "redirect:/diary";
    }

    @GetMapping("/detail/{diaryId}")
    public String getDiaryDetailPage(@PathVariable long diaryId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Diary diary = diaryService.getDiaryByDiaryId(diaryId, userDetails.getUserId());
        model.addAttribute("diary", diary);
        return "diary/detail";
    }
}
