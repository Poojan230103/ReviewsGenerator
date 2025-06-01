package org.spd.backend.exception;

public class InvalidAnswerException extends RuntimeException {
    public InvalidAnswerException(long questionId, String message) {
        super(message);
    }
}
