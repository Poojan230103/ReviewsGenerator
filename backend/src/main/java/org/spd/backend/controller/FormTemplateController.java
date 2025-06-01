package org.spd.backend.controller;

import lombok.RequiredArgsConstructor;
import org.spd.backend.dto.FormTemplateDto;
import org.spd.backend.dto.FormTemplateRequest;
import org.spd.backend.entity.FormTemplate;
import org.spd.backend.service.FormTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/templates/")
public class FormTemplateController {

    private FormTemplateService templateService;

    @Autowired
    public FormTemplateController(FormTemplateService formTemplateService) {
        this.templateService = formTemplateService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FormTemplateRequest request) {
        templateService.createFormTemplate(request);
        return ResponseEntity.ok("Template Created Successfully");
    }

    @GetMapping("/get-all-by-company/{companyId}")
    public List<FormTemplateDto> getAll(@PathVariable Long companyId) {
        return templateService.getAll(companyId);
    }

    @GetMapping("/get-by-template/{templateId}")
    public FormTemplateDto getByTemplateId(@PathVariable long templateId) {
        return templateService.getByTemplateId(templateId);
    }
}
