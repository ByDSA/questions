package dsa.questions.basic;

import java.util.Objects;

@SuppressWarnings("unused")
public class QuestionChoiceString<ID> extends QuestionChoice<ID, String> {
    private String questionString;

    @SuppressWarnings("unused")
    public QuestionChoiceString() {
        super();
    }

    @SuppressWarnings("unused")
    public QuestionChoiceString(String q, Choice<ID, String>[] cs, ID sol) {
        super(cs, sol);

        setQuestionString(questionString);
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
}
