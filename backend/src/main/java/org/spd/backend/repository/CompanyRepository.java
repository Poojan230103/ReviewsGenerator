package org.spd.backend.repository;

import org.spd.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByNameAndEmailAndPassword(String name, String email, String password);

}
