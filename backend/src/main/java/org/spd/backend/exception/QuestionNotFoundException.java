package org.spd.backend.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(long questionId) {
        super("QuestionId = " + questionId + " not found!");
    }
}
