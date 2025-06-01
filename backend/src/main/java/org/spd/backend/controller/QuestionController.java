package org.spd.backend.controller;

import lombok.RequiredArgsConstructor;
import org.spd.backend.config.QuestionConfig;
import org.spd.backend.dto.CreateQuestionRequest;
import org.spd.backend.dto.QuestionDTO;
import org.spd.backend.entity.FormQuestion;
import org.spd.backend.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates/{templateId}/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(
            @PathVariable Long templateId,
            @RequestBody CreateQuestionRequest request) {

        FormQuestion question = questionService.createQuestion(templateId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDto(question));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getTemplateQuestions(
            @PathVariable Long templateId) {

        List<QuestionDTO> questions = questionService.getQuestionsForTemplate(templateId);
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorderQuestions(
            @PathVariable Long templateId,
            @RequestBody List<Long> questionIdsInOrder) {

        questionService.reorderQuestions(templateId, questionIdsInOrder);
        return ResponseEntity.noContent().build();
    }

    private QuestionDTO toDto(FormQuestion question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setType(question.getType());
        dto.setText(question.getText());
        dto.setOrder(question.getOrder());

        QuestionConfig config = question.getConfig();
        if (config != null) {
            dto.setRequired(config.isRequired());
            dto.setMinLength(config.getMinLength());
            dto.setMaxLength(config.getMaxLength());
            dto.setMinValue(config.getMinValue());
            dto.setMaxValue(config.getMaxValue());
            dto.setOptions(config.getOptions());
        }

        return dto;
    }


}
