package org.spd.backend.dto;

import lombok.Data;
import org.spd.backend.enums.CompanyStatusEnum;

import java.time.LocalDateTime;

@Data
public class CompanyDto {

    private String name;
    private String email;
    private CompanyStatusEnum companyStatus;
    private LocalDateTime createdAt;

}
