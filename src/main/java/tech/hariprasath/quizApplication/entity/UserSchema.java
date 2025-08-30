package tech.hariprasath.quizApplication.entity;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.hariprasath.quizApplication.role.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserSchema {
    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    @JsonEnumDefaultValue
    private Role role;
}
