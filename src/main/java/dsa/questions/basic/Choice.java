package dsa.questions.basic;

import dsa.questions.io.json.Idable;

import java.util.Objects;

public abstract class Choice<ID, ANSWER> implements Idable<ID> {
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Choice) {
            Choice c = (Choice) o;
            return getId().equals(c.getId()) && getAnswer().equals(c.getAnswer());
        }

        return false;
    }

    @Override
    public String toString() {
        return "id=" + id + " answer=" + answer;
    }
}