package dsa.questions.basic;

import java.util.Objects;

@SuppressWarnings("unused")
public class QuestionChoiceStringString extends QuestionChoiceString<String, ChoiceString> {
    @SuppressWarnings("unused")
    public QuestionChoiceStringString(String q, ChoiceString[] cs, String sol) {
        super(q, cs, sol);
    }

    @Override
    public void addChoice(String id, String a)  {
        ChoiceString c = new ChoiceString(id, a);
        addChoice(c);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof QuestionChoiceStringString) {
            return super.equals(o);
        }

        return false;
    }
}
