package tech.hariprasath.quizApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.hariprasath.quizApplication.dto.QuizResponse;
import tech.hariprasath.quizApplication.entity.QuizSchema;
import tech.hariprasath.quizApplication.entity.QuizQuestion;
import tech.hariprasath.quizApplication.entity.UserSchema;
import tech.hariprasath.quizApplication.customexception.QuizNotFoundException;
import tech.hariprasath.quizApplication.repository.QuizRepository;
import tech.hariprasath.quizApplication.repository.UserRepository;
import tech.hariprasath.quizApplication.utils.Gemini;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final Gemini gemini;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizResponse createQuiz(int noOfQuestions, String title)  {
        List<QuizQuestion> questions = gemini.createQuiz(noOfQuestions, title);
        return convertToQuizResponse(questions, title, "Quiz in the topic " +title+ " is created");
    }

    public QuizResponse saveQuiz(String title, List<QuizQuestion> questions, String email) {
        UserSchema user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        QuizSchema newQuizSchema = QuizSchema.builder().title(title).questions(questions).user(user).build();
        quizRepository.save(newQuizSchema);
        return convertToQuizResponse(questions, title, "Quiz have been saved successfully");
    }

    public QuizResponse updateQuiz(String title, List<QuizQuestion> quizQuestions, String id) {
        QuizSchema existingQuizSchema = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
        existingQuizSchema.setTitle(title);
        existingQuizSchema.setQuestions(quizQuestions);
        quizRepository.save(existingQuizSchema);
        return convertToQuizResponse(quizQuestions, title, "Quiz have been updated successfully");
    }

   public List<QuizSchema> getAllQuizzes(String email) {
       UserSchema user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return quizRepository.findAllByUser(user);
    }

    public QuizSchema getQuiz(String id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
    }

    public void deleteQuizById(String id) {
        quizRepository.deleteById(id);
    }

    private QuizResponse convertToQuizResponse(List<QuizQuestion> questions, String title, String message) {
        return QuizResponse.builder()
                .title(title)
                .quizQuestions(questions)
                .message(message)
                .build();
    }
}
