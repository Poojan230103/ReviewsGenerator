package org.spd.backend.service;

import lombok.RequiredArgsConstructor;
import org.spd.backend.entity.FormQuestion;
import org.spd.backend.exception.InvalidAnswerException;
import org.spd.backend.exception.MissingAnswerException;
import org.spd.backend.exception.QuestionNotFoundException;
import org.spd.backend.exception.ValidationException;
import org.spd.backend.repository.FormQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResponseValidationService {

    private final FormQuestionRepository questionRepository;

    public void validateResponse(Long templateId, Map<Long, Object> answers) {
        List<FormQuestion> questions = questionRepository.findByTemplateId(templateId);

        // Validate all required questions are answered
        questions.stream()
                .filter(q -> q.getConfig().isRequired())
                .filter(q -> !answers.containsKey(q.getId()))
                .findFirst()
                .ifPresent(q -> {
                    throw new MissingAnswerException(q.getId(), q.getText());
                });

        // Validate each answer
        answers.forEach((questionId, answer) -> {
            FormQuestion question = questions.stream()
                    .filter(q -> q.getId().equals(questionId))
                    .findFirst()
                    .orElseThrow(() -> new QuestionNotFoundException(questionId));

            try {
                question.validateAnswer(answer);
            } catch (ValidationException ex) {
                throw new InvalidAnswerException(questionId, ex.getMessage());
            }
        });
    }


}
