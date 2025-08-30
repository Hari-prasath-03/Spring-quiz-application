package tech.hariprasath.quizApplication.customexception;

public class UserAlreadyExist extends RuntimeException {

    public UserAlreadyExist(String message) {
        super(message);
    }
}
