package tech.hariprasath.quizApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamEvaluationResponse {

    private String title;
    private int totalMarks;
    private int marksAwarded;
    private Map<Integer, Map<String, String>> evaluations;
}
