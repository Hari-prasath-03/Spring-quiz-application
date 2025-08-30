package tech.hariprasath.quizApplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import tech.hariprasath.quizApplication.dto.*;
import tech.hariprasath.quizApplication.entity.QuizSchema;
import tech.hariprasath.quizApplication.service.QuizResultService;
import tech.hariprasath.quizApplication.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuizResultService quizResultService;

    @PostMapping("/create")
    public ResponseEntity<QuizResponse> gem(@Valid @RequestBody QuizCreationRequest request)  {
        QuizResponse quiz = quizService.createQuiz(request.getCount(), request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }

    @PostMapping("/save")
    public ResponseEntity<QuizResponse> saveQuiz(@Valid @RequestBody QuizSaveRequest request, @CurrentSecurityContext(expression = "authentication?.name") String email)  {
        QuizResponse quiz = quizService.saveQuiz(request.getTitle(), request.getQuizQuestions(), email);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }

    @PutMapping("/update")
    public ResponseEntity<QuizResponse> updateQuiz(@Valid @RequestBody QuizUpdateRequest request) {
        QuizResponse quiz = quizService.updateQuiz(request.getTitle(), request.getQuestions(), request.getId());
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("id") String id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<QuizSchema>> getAllQuiz(@CurrentSecurityContext(expression = "authentication?.name") String email) {
        List<QuizSchema> quizSchemas = quizService.getAllQuizzes(email);
        return ResponseEntity.status(HttpStatus.OK).body(quizSchemas);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<QuizSchema> getQuiz(@PathVariable("id") String id) {
        QuizSchema quizzes = quizService.getQuiz(id);
        return ResponseEntity.status(HttpStatus.OK).body(quizzes);
    }

    @GetMapping("/get-quiz-results")
    public ResponseEntity<List<QuizResultsResponse>> getQuizResult(@RequestParam("quizId") String quizId) {
        List<QuizResultsResponse> response = quizResultService.getAllResults(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
