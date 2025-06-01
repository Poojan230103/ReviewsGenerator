package org.spd.backend.exception;

public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException(Long templateId) {
        super("Template " + templateId + " not found!");
    }
}
