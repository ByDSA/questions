package dsa.questions.random;

import dsa.questions.core.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class PackQuestionTarget<Q extends Question> extends PackTarget<QuestionTarget<Q>> {
    private Map<Q, QuestionTarget<Q>> questionTarget = new HashMap<>();

    public PackQuestionTarget() {
        super();
    }

    public PackQuestionTarget(List<Q> qs) {
        for (Q q : qs) {
            add(q);
        }
    }

    @Override
    public boolean add(QuestionTarget<Q> t) {
        questionTarget.put(t.getValue(), t);
        return super.add(t);
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public boolean add(Q q) {
        QuestionTarget<Q> t = new QuestionTarget<>(q);
        return add(t);
    }

    @Override
    public boolean remove(Object t) {
        @SuppressWarnings("unchecked")
        QuestionTarget<Q> st = (QuestionTarget<Q>)t;
        questionTarget.remove(st.getValue());
        return super.remove(t);
    }

    public boolean remove(Q q) {
        return remove(questionTarget.get(q));
    }

    public QuestionTarget<Q> getTarget(Q q) {
        return questionTarget.get(q);
    }
}
