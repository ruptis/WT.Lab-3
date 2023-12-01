package by.bsuir.likeit.controller;

import by.bsuir.likeit.dto.request.AuthenticationRequest;
import by.bsuir.likeit.dto.request.RegistrationRequest;
import by.bsuir.likeit.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("request", new AuthenticationRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("request", new RegistrationRequest());
        return "register";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("request") AuthenticationRequest authenticationRequest) {
        authenticationService.authenticate(authenticationRequest);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegistrationRequest registrationRequest) {
        authenticationService.register(registrationRequest);
        return "redirect:/login";
    }
}
