package org.spd.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.spd.backend.config.QuestionConfig;
import org.spd.backend.enums.QuestionTypeEnum;
import org.spd.backend.exception.ValidationException;

@Entity
@DiscriminatorValue("RATING")
public class RatingQuestion extends FormQuestion {

    @Override
    public QuestionTypeEnum getType() {
        return QuestionTypeEnum.RATING_1_TO_5; // Or could be configurable
    }

    @Override
    protected void validateSpecificAnswer(Object answer) {
        if (!(answer instanceof Integer)) {
            throw new ValidationException("Rating question requires integer answer");
        }

        int rating = (Integer) answer;
        QuestionConfig config = getConfig();
        int min = config.getMinValue() != null ? config.getMinValue() : 1;
        int max = config.getMaxValue() != null ? config.getMaxValue() : 5;

        if (rating < min || rating > max) {
            throw new ValidationException("Rating must be between " + min + " and " + max);
        }
    }

}
