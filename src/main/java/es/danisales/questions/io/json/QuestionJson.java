package es.danisales.questions.io.json;

import com.google.gson.JsonSerializer;
import es.danisales.questions.core.Question;

import java.util.HashMap;
import java.util.Map;

public abstract class QuestionJson<Q extends Question> implements JsonSerializer<Q> {
    long id;
    String type;

    static final Map<Long, QuestionJson> idToJson = new HashMap<>();

    public QuestionJson(String type) {
        this.type = type;
    }
}
