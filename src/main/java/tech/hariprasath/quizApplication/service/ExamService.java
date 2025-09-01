package tech.hariprasath.quizApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hariprasath.quizApplication.customexception.QuizNotFoundException;
import tech.hariprasath.quizApplication.dto.ExamEvaluationResponse;
import tech.hariprasath.quizApplication.dto.ExamQuestionsResponse;
import tech.hariprasath.quizApplication.entity.ExamQuestions;
import tech.hariprasath.quizApplication.entity.QuizQuestion;
import tech.hariprasath.quizApplication.entity.QuizSchema;
import tech.hariprasath.quizApplication.repository.QuizRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final QuizRepository quizRepository;
    private final QuizResultService quizResultService;

    public ExamQuestionsResponse getText(String quizId) {
        QuizSchema quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
        return convertToExamQuestionResponse(quiz);
    }

    private ExamQuestionsResponse convertToExamQuestionResponse(QuizSchema quiz) {
        return ExamQuestionsResponse.builder()
                .title(quiz.getTitle())
                .questions(quiz
                        .getQuestions()
                        .stream()
                        .map(q -> ExamQuestions
                                .builder()
                                .id(q.getId())
                                .question(q.getQuestion())
                                .options(q.getOptions())
                                .build()
                        )
                        .toList()
                )
                .build();
    }

    public ExamEvaluationResponse evaluateTest(String id, Map<Integer, String> chosenOptions, String email) {
        QuizSchema quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
        List<QuizQuestion> quizQuestions = quiz.getQuestions();

        int score = 0;
        Map<Integer, Map<String, String>> evaluation = new HashMap<>();

        for (QuizQuestion question : quizQuestions) {
            if (chosenOptions.containsKey(question.getId())) {
                String correctOption = question.getCorrectOption();
                String chosenOption = chosenOptions.get(question.getId());
                evaluation.put(question.getId(), Map.of("correctOption", correctOption, "chosenOption", chosenOption));
                if (correctOption.equals(chosenOption)) score++;
            }
        }
        quizResultService.saveResult(quiz, email, score,  evaluation);
        return buildExamEvaluationResponse(quiz.getTitle(), quiz.getQuestions().size(), score, evaluation);
    }

    private ExamEvaluationResponse buildExamEvaluationResponse(String title, int totalMarks, int score, Map<Integer, Map<String, String>> evaluation) {
        return ExamEvaluationResponse.builder()
                .title(title)
                .totalMarks(totalMarks)
                .marksAwarded(score)
                .evaluations(evaluation)
                .build();
    }
}
