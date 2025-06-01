package org.spd.backend.repository;

import org.spd.backend.entity.FormTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

    List<FormTemplate> getAllByCompany_Id(long companyId);

}
