package by.bsuir.likeit.controller;

import by.bsuir.likeit.dto.request.AnswerRequest;
import by.bsuir.likeit.dto.request.QuestionRequest;
import by.bsuir.likeit.entity.Question;
import by.bsuir.likeit.service.AnswerService;
import by.bsuir.likeit.service.QuestionService;
import by.bsuir.likeit.service.UserService;
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

    public static final int PAGE_SIZE = 10;

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @GetMapping("/{id}")
    public String question(@RequestParam(defaultValue = "0") int page, @PathVariable long id, Model model, Principal principal) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("questionRequest", new QuestionRequest(question.getTopic().getId(), question.getTitle(), question.getText()));
        model.addAttribute("answerRequest", new AnswerRequest());
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("question", question);
        model.addAttribute("answers", answerService.getAllAnswersByQuestion(id, page, PAGE_SIZE));
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

    @PostMapping("/{id}")
    public String updateQuestion(@PathVariable long id, @Valid @ModelAttribute("questionRequest") QuestionRequest questionRequest) {
        questionService.updateQuestion(questionRequest, id);
        return "redirect:/question";
    }
}
