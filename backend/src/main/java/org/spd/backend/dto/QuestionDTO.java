package org.spd.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.spd.backend.config.QuestionConfig;
import org.spd.backend.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionDTO {

    private Long id;
    private QuestionTypeEnum type;
    private String text;
    private boolean required;
    private Integer minLength;
    private Integer maxLength;
    private Integer minValue;
    private Integer maxValue;
    private List<String> options;
    private Integer order;

    public QuestionConfig toConfig() {
        QuestionConfig config = new QuestionConfig();
        config.setRequired(required);
        config.setMinLength(minLength);
        config.setMaxLength(maxLength);
        config.setMinValue(minValue);
        config.setMaxValue(maxValue);
        if (options != null) {
            config.setOptions(new ArrayList<>(options));
        }
        return config;
    }


}
