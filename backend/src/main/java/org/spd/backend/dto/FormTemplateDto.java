package org.spd.backend.dto;

import lombok.Data;
import org.spd.backend.entity.Company;

import java.util.List;

@Data
public class FormTemplateDto {
    private Long id;
    private String title;
    private long companyId;
    List<QuestionDTO> questions;
}
