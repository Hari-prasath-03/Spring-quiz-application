package tech.hariprasath.quizApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestions {

    private int id;
    private String question;
    private Map<String,String> options;
}
