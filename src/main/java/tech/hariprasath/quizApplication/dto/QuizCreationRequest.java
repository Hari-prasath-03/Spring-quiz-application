package tech.hariprasath.quizApplication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizCreationRequest {

    @Min(value = 5, message = "Minimum question count is 5")
    @Max(value = 25, message = "Minimum question count is 25")
    private int count;

    @NotBlank(message = "You cannot create a quiz without title")
    private String title;
}
