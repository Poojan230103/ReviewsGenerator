package org.spd.backend.config;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
public class QuestionConfig {

    private boolean required;
    private Integer minLength;
    private Integer maxLength;
    private Integer minValue;
    private Integer maxValue;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    private List<String> options = new ArrayList<>();

}
