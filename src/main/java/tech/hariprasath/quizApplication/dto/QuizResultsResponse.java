package tech.hariprasath.quizApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultsResponse {

    private Student student;
    private int score;
    private LocalDateTime submittedAt;
    private Map<Integer, Map<String, String>> quizResults;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {
        private String name;
        private String email;
    }
}
