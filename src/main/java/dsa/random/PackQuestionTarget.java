package dsa.random;

import dsa.questions.core.Question;
import dsa.random.target.PackTarget;
import dsa.random.target.Target;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class PackQuestionTarget<PICK_TYPE extends QuestionTarget<? extends Question>> extends PackTarget<PICK_TYPE> {
    private Map<Question, PICK_TYPE> questionTarget = new HashMap<>();

    public PackQuestionTarget() {
        super();
    }

    public PackQuestionTarget(List<Question> qs) {
        for (Question q : qs) {
            add(q);
        }
    }

    @Override
    public boolean add(Target t) {
        if (t instanceof QuestionTarget) {
            PICK_TYPE qt = (PICK_TYPE) t;
            questionTarget.put(qt.getValue(), qt);
        }
        return super.add(t);
    }

    @SuppressWarnings({"WeakerAccess", "unused", "UnusedReturnValue"})
    public boolean add(Question q) {
        QuestionTarget t = new QuestionTarget<>(q);
        return add(t);
    }

    @Override
    public boolean remove(Object t) {
        QuestionTarget st = (QuestionTarget) t;
        questionTarget.remove(st.getValue());
        return super.remove(t);
    }

    public boolean remove(Question q) {
        return remove(questionTarget.get(q));
    }

    public PICK_TYPE getTarget(Question q) {
        return questionTarget.get(q);
    }
}
