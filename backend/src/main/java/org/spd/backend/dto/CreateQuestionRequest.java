package org.spd.backend.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.spd.backend.config.QuestionConfig;
import org.spd.backend.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateQuestionRequest {

    @NonNull
    private QuestionTypeEnum type;

    private String text;

    private boolean required = true;
    private Integer minLength;
    private Integer maxLength;
    private Integer minValue;
    private Integer maxValue;
    private List<String> options;
    private Integer order;

    // Converts request to QuestionConfig
    public QuestionConfig toConfig() {
        QuestionConfig config = new QuestionConfig();
        config.setRequired(this.required);
        config.setMinLength(this.minLength);
        config.setMaxLength(this.maxLength);
        config.setMinValue(this.minValue);
        config.setMaxValue(this.maxValue);

        if (this.options != null) {
            config.setOptions(new ArrayList<>(this.options));
        }

        return config;
    }



}
