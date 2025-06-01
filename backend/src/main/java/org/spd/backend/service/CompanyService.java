package org.spd.backend.service;

import jakarta.servlet.http.PushBuilder;
import org.spd.backend.dto.CompanyDto;
import org.spd.backend.dto.CompanyRequest;
import org.spd.backend.entity.Company;
import org.spd.backend.enums.CompanyStatusEnum;
import org.spd.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDto register(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPassword(request.getPassword());
        company.setStatus(CompanyStatusEnum.ACTIVE);
        company.setCreatedAt(LocalDateTime.now());
        return toDto(companyRepository.save(company));
    }

    public CompanyDto get(CompanyRequest request) throws Exception {
        Optional<Company> companyOptional = companyRepository.findByNameAndEmailAndPassword(request.getName(), request.getEmail(), request.getPassword());
        if (companyOptional.isPresent()) {
            return toDto(companyOptional.get());
        }
        else {
            throw new Exception("Company Not Found");
        }
    }

    public CompanyDto toDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setCompanyStatus(company.getStatus());
        companyDto.setEmail(company.getEmail());
        companyDto.setCreatedAt(company.getCreatedAt());
        return companyDto;
    }


}
