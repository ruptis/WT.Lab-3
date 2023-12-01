package by.bsuir.likeit.controller;

import by.bsuir.likeit.dto.request.QuestionRequest;
import by.bsuir.likeit.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("")
    public String question(Model model) {
        return "question";
    }

    @PostMapping("")
    public String addQuestion(@Valid @ModelAttribute("question") QuestionRequest questionRequest, Principal principal) {
        questionService.addQuestion(questionRequest, principal.getName());
        return "redirect:/question";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable long id) {
        questionService.deleteQuestion(id);
        return "redirect:/question";
    }

    @PutMapping("/{id}")
    public String updateQuestion(@PathVariable long id, @Valid @ModelAttribute("question") QuestionRequest questionRequest) {
        questionService.updateQuestion(questionRequest, id);
        return "redirect:/question";
    }
}
