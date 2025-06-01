package org.spd.backend.exception;

public class MissingAnswerException extends RuntimeException {
    public MissingAnswerException(long questionId, String answer) {
        super("Missing Answer Exception");
    }
}
