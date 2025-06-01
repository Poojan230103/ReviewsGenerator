package org.spd.backend.controller;

import lombok.RequiredArgsConstructor;
import org.spd.backend.dto.CompanyDto;
import org.spd.backend.dto.CompanyRequest;
import org.spd.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public CompanyDto register(@RequestBody CompanyRequest request) {
        return companyService.register(request);
    }

    @GetMapping("/get")
    public CompanyDto get(@RequestBody CompanyRequest request) throws Exception {
        return companyService.get(request);
    }
}
