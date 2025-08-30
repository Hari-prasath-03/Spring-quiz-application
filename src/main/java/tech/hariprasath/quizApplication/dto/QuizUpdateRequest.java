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
public class QuizUpdateRequest {

    @NotBlank(message = "Id is required to update document")
    private String id;
    private String title;
    private List<QuizQuestion> questions;
}
