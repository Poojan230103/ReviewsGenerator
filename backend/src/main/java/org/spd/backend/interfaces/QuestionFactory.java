package org.spd.backend.interfaces;

import org.spd.backend.config.QuestionConfig;
import org.spd.backend.entity.FormQuestion;
import org.spd.backend.enums.QuestionTypeEnum;

public interface QuestionFactory {

    FormQuestion createQuestion(QuestionTypeEnum type, String text, QuestionConfig config);
}
