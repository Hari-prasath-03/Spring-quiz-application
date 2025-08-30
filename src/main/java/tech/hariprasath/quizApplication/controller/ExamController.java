package tech.hariprasath.quizApplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import tech.hariprasath.quizApplication.dto.ExamEvaluationRequest;
import tech.hariprasath.quizApplication.dto.ExamEvaluationResponse;
import tech.hariprasath.quizApplication.dto.ExamQuestionsResponse;
import tech.hariprasath.quizApplication.service.ExamService;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping("/take-test")
    public ResponseEntity<ExamQuestionsResponse> attendTest(@RequestParam String quizId) {
        ExamQuestionsResponse exam = examService.getText(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(exam);
    }

    @PostMapping("/evaluate-test")
    public ResponseEntity<ExamEvaluationResponse> evaluateTest(@RequestBody ExamEvaluationRequest request, @CurrentSecurityContext(expression = "authentication?.name") String email) {
        ExamEvaluationResponse response = examService.evaluateTest(request.getId(), request.getChosenOptions(), email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
