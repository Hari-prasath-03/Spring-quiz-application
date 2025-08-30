package tech.hariprasath.quizApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import tech.hariprasath.quizApplication.role.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;

    @Email(message = "Please provide a valid email")
    private String email;

    @Length(min=6, message = "Password must contains at-least 6 characters")
    private String password;

    @NotNull(message = "Please specify any one of the role")
    private Role role;
}
