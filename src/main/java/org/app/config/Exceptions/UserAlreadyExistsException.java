package org.app.config.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    private final UserErrorField field;

    public enum UserErrorField {
        USERNAME,
        EMAIL
    }

    public UserAlreadyExistsException(UserErrorField field) {
        this.field = field;
    }

    public UserErrorField getField() {
        return field;
    }
}
