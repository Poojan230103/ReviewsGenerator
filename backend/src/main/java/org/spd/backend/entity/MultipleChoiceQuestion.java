package org.spd.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.spd.backend.enums.QuestionTypeEnum;
import org.spd.backend.exception.ValidationException;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
public class MultipleChoiceQuestion extends FormQuestion {

    @Override
    public QuestionTypeEnum getType() {
        return QuestionTypeEnum.MULTIPLE_CHOICE;
    }

    @Override
    protected void validateSpecificAnswer(Object answer) {
        if (!(answer instanceof String)) {
            throw new ValidationException("Multiple choice question requires string answer");
        }

        String choice = (String) answer;
        if (!getConfig().getOptions().contains(choice)) {
            throw new ValidationException("Invalid choice for multiple choice question");
        }
    }


}
