package org.spd.backend.service;

import org.spd.backend.dto.FormTemplateDto;
import org.spd.backend.dto.FormTemplateRequest;
import org.spd.backend.entity.Company;
import org.spd.backend.entity.FormTemplate;
import org.spd.backend.repository.FormTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormTemplateService {

    private final FormTemplateRepository templateRepository;
    private final QuestionService questionService;

    @Autowired
    public FormTemplateService(FormTemplateRepository templateRepository, QuestionService questionService) {
        this.templateRepository = templateRepository;
        this.questionService = questionService;
    }

    public void createFormTemplate(FormTemplateRequest request) {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setTitle(request.getTitle());
        formTemplate.setQuestions(new ArrayList<>());
        formTemplate.setCompany(new Company());
        templateRepository.save(formTemplate);
    }

    public List<FormTemplateDto> getAll(long companyId) {
        List<FormTemplate> formTemplates = templateRepository.getAllByCompany_Id(companyId);
        return formTemplates.stream().map(formTemplate -> toDTO(formTemplate, false)).toList();
    }

    public FormTemplateDto getByTemplateId(long templateId) {
        FormTemplate formTemplate = templateRepository.findById(templateId).get();
        return toDTO(formTemplate, true);
    }

    public FormTemplateDto toDTO(FormTemplate template, boolean needQuestions) {
        FormTemplateDto templateDto = new FormTemplateDto();
        templateDto.setId(template.getId());
        templateDto.setCompanyId(template.getCompany().getId());
        templateDto.setTitle(template.getTitle());
        if (needQuestions)
            templateDto.setQuestions(template.getQuestions().stream().map(questionService::toDTO).toList());
        return templateDto;
    }

}
