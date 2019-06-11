package dsa.questions.basic;

import java.util.Objects;

public class Choice<ID, ANSWER> {
    private ID id;
    private ANSWER answer;

    @SuppressWarnings("WeakerAccess")
    public Choice(ID id, ANSWER a) {
        setId(id);
        setAnswer(a);
    }

    @SuppressWarnings("WeakerAccess")
    public ID getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public void setId(ID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    @SuppressWarnings("unused")
    public ANSWER getAnswer() {
        return answer;
    }

    @SuppressWarnings("WeakerAccess")
    public void setAnswer(ANSWER a) {
        Objects.requireNonNull(a);
        answer = a;
    }
}