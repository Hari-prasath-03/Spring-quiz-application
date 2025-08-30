package tech.hariprasath.quizApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quiz_results")
public class QuizResultSchema {

    @Id
    private String id;
    @DBRef
    private UserSchema user;
    @DBRef
    private QuizSchema quiz;
    private int score;
    private LocalDateTime submittedAt;
    private Map<Integer, Map<String, String>> selectedAnswers;
}
