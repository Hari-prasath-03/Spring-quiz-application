package tech.hariprasath.quizApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.hariprasath.quizApplication.customexception.QuizNotFoundException;
import tech.hariprasath.quizApplication.dto.QuizResultsResponse;
import tech.hariprasath.quizApplication.entity.QuizResultSchema;
import tech.hariprasath.quizApplication.entity.QuizSchema;
import tech.hariprasath.quizApplication.entity.UserSchema;
import tech.hariprasath.quizApplication.repository.QuizRepository;
import tech.hariprasath.quizApplication.repository.QuizResultRepository;
import tech.hariprasath.quizApplication.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuizResultService {
    private final UserRepository userRepository;
    private final QuizResultRepository quizResultRepository;
    private final QuizRepository quizRepository;

    public void saveResult(QuizSchema quiz, String email, int score, Map<Integer, Map<String, String>> selectedAnswers) {
        UserSchema user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        QuizResultSchema result = QuizResultSchema.builder()
                .quiz(quiz)
                .user(user)
                .score(score)
                .selectedAnswers(selectedAnswers)
                .submittedAt(LocalDateTime.now())
                .build();
        quizResultRepository.save(result);
    }

    public List<QuizResultsResponse> getAllResults(String quizId) {
        QuizSchema quiz = quizRepository.findById(quizId)
                        .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
        List<QuizResultSchema> quizResults = quizResultRepository.findAllByQuiz(quiz);
        return convertToQuizResultResponse(quizResults);
    }

    private List<QuizResultsResponse> convertToQuizResultResponse(List<QuizResultSchema> quizResults) {
        return quizResults
                .stream()
                .map(quizRes -> QuizResultsResponse
                        .builder()
                        .student(
                                QuizResultsResponse
                                        .Student
                                        .builder()
                                        .name(quizRes.getUser().getName())
                                        .email(quizRes.getUser().getEmail())
                                        .build()
                        )
                        .score(quizRes.getScore())
                        .submittedAt(quizRes.getSubmittedAt())
                        .quizResults(quizRes.getSelectedAnswers())
                        .build()
                )
                .toList();
    }
}
