package tech.hariprasath.quizApplication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String name;
    private String email;
    private String message;
}
