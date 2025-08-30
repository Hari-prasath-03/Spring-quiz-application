package tech.hariprasath.quizApplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@PathVariable String country, @PathVariable String date) {
        return "Welcome to my new Spring Boot Quiz application!";
    }
}
