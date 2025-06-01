package org.spd.backend.dto;

import lombok.Data;

@Data
public class CompanyRequest {

    private String name;
    private String email;
    private String password;

}
