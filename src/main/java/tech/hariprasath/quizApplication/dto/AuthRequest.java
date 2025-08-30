package tech.hariprasath.quizApplication.dto;

import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Email(message = "Please provide a valid email")
    private String email;

    @Length(min = 6, message = "Password must contains at-least 6 characters")
    private String password;
}
