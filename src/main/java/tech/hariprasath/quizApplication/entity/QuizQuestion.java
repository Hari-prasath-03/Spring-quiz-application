package tech.hariprasath.quizApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {

    private int id;
    private String question;
    private Map<String,String> options;
    private String correctOption;
}
