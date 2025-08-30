package tech.hariprasath.quizApplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import tech.hariprasath.quizApplication.dto.UserRequest;
import tech.hariprasath.quizApplication.dto.UserResponse;
import tech.hariprasath.quizApplication.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest user) {
        UserResponse newUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/profile")
    public UserResponse getProfile(@CurrentSecurityContext(expression = "authentication?.name") String email) {
        return userService.getProfile(email);
    }
}
