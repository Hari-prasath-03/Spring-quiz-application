package tech.hariprasath.quizApplication.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.hariprasath.quizApplication.customexception.QuizNotFoundException;
import tech.hariprasath.quizApplication.customexception.UserAlreadyExist;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExist.class)
    public ProblemDetail handleException(UserAlreadyExist ex) {
        ProblemDetail errorResponse = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        errorResponse.setProperty("message", ex.getMessage());
        errorResponse.setProperty("success", false);
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleException(MethodArgumentNotValidException ex) {
        ProblemDetail errorResponse = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setProperty("message", ex.getBindingResult().getFieldErrors().stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("Validation error"));
        errorResponse.setProperty("success", false);
        return errorResponse;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleException(BadCredentialsException ex) {
        ProblemDetail errorResponse = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        errorResponse.setProperty("message", "Email or password is incorrect");
        errorResponse.setProperty("success", false);
        return errorResponse;
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ProblemDetail handleException(QuizNotFoundException ex) {
        ProblemDetail errorResponse = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        errorResponse.setProperty("message", ex.getMessage());
        errorResponse.setProperty("success", false);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        ProblemDetail errorResponse = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setProperty("success", false);
        errorResponse.setProperty("message", ex.getMessage());
        return errorResponse;
    }
}
