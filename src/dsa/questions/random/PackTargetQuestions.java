package dsa.questions.random;

import dsa.questions.core.Question;

import java.util.List;

@SuppressWarnings("unused")
public class PackTargetQuestions<Q extends Question> extends PackTarget<SimpleTargetBean<Q>> {

    public PackTargetQuestions() {
        super();
    }

    public PackTargetQuestions(List<Q> qs) {
        for (Q q : qs) {
            SimpleTargetBean<Q> t = new SimpleTargetBean<Q>(q);
            add(t);
        }
    }
}
