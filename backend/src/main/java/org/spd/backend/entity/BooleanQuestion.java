package org.spd.backend.entity;

import org.spd.backend.enums.QuestionTypeEnum;
import org.spd.backend.exception.ValidationException;

public class BooleanQuestion extends FormQuestion {

    @Override
    public QuestionTypeEnum getType() {
        return QuestionTypeEnum.BOOLEAN;
    }

    @Override
    protected void validateSpecificAnswer(Object answer) {
        if (!(answer instanceof Boolean)) {
            throw new ValidationException("Text question requires string answer");
        }
    }
}
