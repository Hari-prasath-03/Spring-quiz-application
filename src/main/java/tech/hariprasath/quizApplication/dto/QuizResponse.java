package tech.hariprasath.quizApplication.dto;

import lombok.Data;
import lombok.Builder;
import tech.hariprasath.quizApplication.entity.QuizQuestion;

import java.util.List;

@Data
@Builder
public class QuizResponse {

    private String title;
    private List<QuizQuestion> quizQuestions;
    private String message;
}
