package org.spd.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "form_templates")
@Getter
@Setter
public class FormTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormQuestion> questions = new ArrayList<>();

    @Version
    private Long version;

    public static class Builder {
        private String title;
        private Company company;
        private List<FormQuestion> questions = new ArrayList<>();

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withCompany(Company company) {
            this.company = company;
            return this;
        }

        public Builder addQuestion(FormQuestion question) {
            this.questions.add(question);
            return this;
        }

        public FormTemplate build() {
            FormTemplate template = new FormTemplate();
            template.setTitle(title);
            template.setCompany(company);
            questions.forEach(q -> q.setTemplate(template));
            template.setQuestions(questions);
            return template;
        }
    }
}
