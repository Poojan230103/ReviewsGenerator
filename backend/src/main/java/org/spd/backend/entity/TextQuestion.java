package org.spd.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.spd.backend.config.QuestionConfig;
import org.spd.backend.enums.QuestionTypeEnum;
import org.spd.backend.exception.ValidationException;

@Entity
@DiscriminatorValue("TEXT")
public class TextQuestion extends FormQuestion {

    @Override
    public QuestionTypeEnum getType() {
        return QuestionTypeEnum.TEXT;
    }

    @Override
    protected void validateSpecificAnswer(Object answer) {
        if (!(answer instanceof String)) {
            throw new ValidationException("Text question requires string answer");
        }

        String textAnswer = (String) answer;
        QuestionConfig config = getConfig();

        if (config.getMinLength() != null && textAnswer.length() < config.getMinLength()) {
            throw new ValidationException("Answer must be at least " + config.getMinLength() + " characters");
        }

        if (config.getMaxLength() != null && textAnswer.length() > config.getMaxLength()) {
            throw new ValidationException("Answer must be at most " + config.getMaxLength() + " characters");
        }
    }


}
