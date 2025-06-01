package org.spd.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.spd.backend.entity.Company;

@Getter
@Setter
public class FormTemplateRequest {

    private String title;
    private long companyId;

}
