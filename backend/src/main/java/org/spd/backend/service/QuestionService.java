package org.spd.backend.service;

import lombok.RequiredArgsConstructor;
import org.spd.backend.dto.CreateQuestionRequest;
import org.spd.backend.dto.QuestionDTO;
import org.spd.backend.entity.FormQuestion;
import org.spd.backend.entity.FormTemplate;
import org.spd.backend.exception.TemplateNotFoundException;
import org.spd.backend.interfaces.QuestionFactory;
import org.spd.backend.repository.FormQuestionRepository;
import org.spd.backend.repository.FormTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final FormQuestionRepository questionRepository;
    private final FormTemplateRepository templateRepository;
    private final QuestionFactory questionFactory;

    public FormQuestion createQuestion(Long templateId, CreateQuestionRequest request) {
        FormTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new TemplateNotFoundException(templateId));

        FormQuestion question = questionFactory.createQuestion(
                request.getType(),
                request.getText(),
                request.toConfig()
        );

        question.setTemplate(template);
        question.setOrder(request.getOrder());

        return questionRepository.save(question);
    }

    public List<FormQuestion> reorderQuestions(Long templateId, List<Long> questionIdsInOrder) {
        List<FormQuestion> questions = questionRepository.findByTemplateId(templateId);

        Map<Long, FormQuestion> questionMap = questions.stream()
                .collect(Collectors.toMap(FormQuestion::getId, Function.identity()));

        List<FormQuestion> orderedQuestions = new ArrayList<>();
        for (int i = 0; i < questionIdsInOrder.size(); i++) {
            Long questionId = questionIdsInOrder.get(i);
            FormQuestion question = questionMap.get(questionId);
            if (question != null) {
                question.setOrder(i + 1);
                orderedQuestions.add(question);
            }
        }

        return questionRepository.saveAll(orderedQuestions);
    }

    public List<QuestionDTO> getQuestionsForTemplate(Long templateId) {
        return questionRepository.findByTemplateId(templateId).stream().map(this::toDTO).toList();
    }

    public QuestionDTO toDTO(FormQuestion question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setOptions(question.getConfig().getOptions());
        questionDTO.setRequired(question.getConfig().isRequired());
        questionDTO.setMinLength(question.getConfig().getMinLength());
        questionDTO.setMaxLength(question.getConfig().getMaxLength());
        questionDTO.setOrder(question.getOrder());
        questionDTO.setText(question.getText());
        questionDTO.setType(question.getType());
        return questionDTO;
    }

}
