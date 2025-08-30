package tech.hariprasath.quizApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quiz_s")
public class QuizSchema {

    @Id
    private String id;
    private String title;
    private List<QuizQuestion> questions;
    @DBRef
    @JsonIgnore
    private UserSchema user;
}
