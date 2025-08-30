package tech.hariprasath.quizApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamEvaluationRequest {

    private String id;
    private Map<Integer, String> chosenOptions;
}
