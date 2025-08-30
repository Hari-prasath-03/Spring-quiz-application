package tech.hariprasath.quizApplication.dto;

import lombok.Builder;
import lombok.Data;
import tech.hariprasath.quizApplication.entity.ExamQuestions;

import java.util.List;

@Data
@Builder
public class ExamQuestionsResponse {

    private String title;
    private List<ExamQuestions> questions;

}
