package by.bsuir.likeit.controller;

import by.bsuir.likeit.dto.request.QuestionRequest;
import by.bsuir.likeit.entity.Topic;
import by.bsuir.likeit.service.QuestionService;
import by.bsuir.likeit.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private static final int PAGE_SIZE = 10;
    private static final int TOPICS_SIZE = 10;

    private final TopicService topicService;
    private final QuestionService questionService;

    @GetMapping("")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {
        List<Topic> topics = topicService.getAllTopics();
        log.info("Topics: {}", topics);
        model.addAttribute("question", new QuestionRequest());
        model.addAttribute("topics", topicService.getAllTopics(page, TOPICS_SIZE));
        model.addAttribute("questions", questionService.getAllQuestions(page, PAGE_SIZE));
        return "home";
    }
}
