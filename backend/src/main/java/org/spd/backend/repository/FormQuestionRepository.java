package org.spd.backend.repository;

import org.spd.backend.entity.FormQuestion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {

    @EntityGraph(attributePaths = {"template"})
    List<FormQuestion> findByTemplateId(Long templateId);

    @Query("SELECT q FROM FormQuestion q WHERE q.template.id = :templateId ORDER BY q.order ASC")
    List<FormQuestion> findOrderedByTemplateId(Long templateId);

    @Modifying
    @Query("UPDATE FormQuestion q SET q.order = :order WHERE q.id = :id")
    void updateQuestionOrder(Long id, Integer order);


}
