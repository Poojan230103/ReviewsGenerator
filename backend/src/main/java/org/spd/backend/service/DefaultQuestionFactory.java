package org.spd.backend.service;

import org.spd.backend.config.QuestionConfig;
import org.spd.backend.entity.*;
import org.spd.backend.enums.QuestionTypeEnum;
import org.spd.backend.interfaces.QuestionFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultQuestionFactory implements QuestionFactory {

    @Override
    public FormQuestion createQuestion(QuestionTypeEnum type, String text, QuestionConfig config) {
        return switch (type) {
            case TEXT -> {
                TextQuestion question = new TextQuestion();
                question.setText(text);
                question.setConfig(config);
                yield question;
            }
            case RATING_1_TO_5, RATING_1_TO_10 -> {
                RatingQuestion question = new RatingQuestion();
                question.setText(text);
                question.setConfig(config);
                yield question;
            }
            case MULTIPLE_CHOICE, DROPDOWN -> {
                MultipleChoiceQuestion question = new MultipleChoiceQuestion();
                question.setText(text);
                question.setConfig(config);
                yield question;
            }
            case BOOLEAN -> {
                BooleanQuestion question = new BooleanQuestion();
                question.setText(text);
                question.setConfig(config);
                yield question;
            }
            default -> throw new IllegalArgumentException("Unsupported question type: " + type);
        };
    }

}
