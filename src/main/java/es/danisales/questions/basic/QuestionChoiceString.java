package es.danisales.questions.basic;

import java.util.Objects;

@SuppressWarnings("unused")
public abstract class QuestionChoiceString<ID, C extends Choice<ID, String>> extends QuestionChoice<ID, String, C> {
    private String questionString;

    @SuppressWarnings("unused")
    public QuestionChoiceString(String q, C[] cs, ID sol) {
        super(cs, sol);

        setQuestionString(q);
    }

    @SuppressWarnings("unused")
    public final String getQuestionString() {
        return questionString;
    }

    @SuppressWarnings({"WeakerAccess"})
    public final void setQuestionString(String s) {
        Objects.requireNonNull(s);
        questionString = s;
    }

    @Override
    public String toString() {
        return questionString;
    }

    @Override
    public boolean equals(Object o) {
        QuestionChoiceString qs = (QuestionChoiceString) o;
        return getQuestionString().equals(qs.getQuestionString()) && super.equals(o);
    }
}
