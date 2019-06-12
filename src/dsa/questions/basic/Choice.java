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
    public final ID getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public final void setId(ID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    @SuppressWarnings("unused")
    public final ANSWER getAnswer() {
        return answer;
    }

    @SuppressWarnings("WeakerAccess")
    public final void setAnswer(ANSWER a) {
        Objects.requireNonNull(a);
        answer = a;
    }
}