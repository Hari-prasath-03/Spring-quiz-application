package tech.hariprasath.quizApplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.hariprasath.quizApplication.entity.QuizQuestion;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSaveRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;
    private List<QuizQuestion> quizQuestions;
}
