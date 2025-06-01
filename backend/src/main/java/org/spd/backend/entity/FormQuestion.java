package org.spd.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.spd.backend.config.QuestionConfig;
import org.spd.backend.enums.QuestionTypeEnum;
import org.spd.backend.exception.ValidationException;

@Entity
@Table(name = "form_questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class FormQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Embedded
    private QuestionConfig config;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private FormTemplate template;

    @Column(name = "display_order")
    private Integer order;

    @Transient
    public abstract QuestionTypeEnum getType();

    // Common validation logic
    public void validateAnswer(Object answer) {
        if (config.isRequired() && answer == null) {
            throw new ValidationException("Answer is required for question: " + text);
        }

        if (answer != null) {
            validateSpecificAnswer(answer);
        }
    }

    protected abstract void validateSpecificAnswer(Object answer);


}
