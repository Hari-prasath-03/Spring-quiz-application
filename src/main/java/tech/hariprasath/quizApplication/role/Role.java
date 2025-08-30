package tech.hariprasath.quizApplication.role;

public enum Role {
    ADMIN, STUDENT, STAFF;

    public String withPrefix() {
        return "ROLE_" + this.name();
    }
}
