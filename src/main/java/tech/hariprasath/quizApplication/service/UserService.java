package tech.hariprasath.quizApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.hariprasath.quizApplication.dto.UserRequest;
import tech.hariprasath.quizApplication.dto.UserResponse;
import tech.hariprasath.quizApplication.entity.UserSchema;
import tech.hariprasath.quizApplication.customexception.UserAlreadyExist;
import tech.hariprasath.quizApplication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserRequest user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new UserAlreadyExist("Email already exists");
        UserSchema newUser = userRepository.save(convertToUser(user));
        return convertToUserResponse(newUser);
    }

    private UserResponse convertToUserResponse(UserSchema user) {
        final String userName = StringUtils.hasText(user.getName()) ? user.getName() : user.getEmail().split("@")[0];
        return UserResponse.builder()
                .name(userName)
                .email(user.getEmail())
                .message("Registered successfully")
                .build();
    }

    private UserSchema convertToUser(UserRequest userRequest) {
        return UserSchema.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(userRequest.getRole())
                .build();
    }

    public UserResponse getProfile(String email) {
        UserSchema user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToUserResponse(user);
    }
}
