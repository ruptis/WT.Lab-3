package by.bsuir.likeit.controller;

import by.bsuir.likeit.dto.request.AnswerRequest;
import by.bsuir.likeit.entity.Vote;
import by.bsuir.likeit.service.AnswerService;
import by.bsuir.likeit.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final VoteService voteService;

    @PostMapping("")
    public String addAnswer(@Valid @ModelAttribute("answer") AnswerRequest answerRequest, Principal principal) {
        answerService.addAnswer(answerRequest, principal.getName());
        return "redirect:/question/" + answerRequest.getQuestionId();
    }

    @PostMapping("/upvote/{id}")
    public String upvoteAnswer(@PathVariable long id, Principal principal) {
        voteService.upvote(id, principal.getName());
        return "redirect:/question/" + answerService.getAnswer(id).getQuestion().getId();

    }

}
