package dsa.questions.basic;

import com.google.gson.reflect.TypeToken;
import dsa.questions.core.Question;

import java.lang.reflect.Type;
import java.util.*;

public abstract class QuestionChoice<ID, ANSWER, C extends Choice<ID, ANSWER>> extends Question<SolutionBean<ID>, ID> {
    private Map<ID, C> choices = new HashMap<>();
    private List<C> choicesList = new ArrayList<>();

    @SuppressWarnings("WeakerAccess")
    public QuestionChoice(C[] choices, ID s) {
        Objects.requireNonNull(choices);
        Objects.requireNonNull(s);

        for (C c : choices)
            addChoice(c);

        setSolution(s);
    }

    @SuppressWarnings("WeakerAccess")
    public final void addChoice(C c) {
        Objects.requireNonNull(c);

        choices.put(c.getId(), c);
        choicesList.add(c);
    }

    @SuppressWarnings("unused")
    public abstract void addChoice(ID id, ANSWER a);

    @SuppressWarnings("unused")
    public final Choice<ID, ANSWER> getChoice(ID id) {
        Objects.requireNonNull(id);
        return choices.get(id);
    }

    @SuppressWarnings("WeakerAccess")
    public final List<C> getChoices() {
        return choicesList;
    }

    @SuppressWarnings("unused")
    public final List<C> getShuffledChoices() {
        List<C> l = getChoices();
        Collections.shuffle(l);

        return l;
    }

    @SuppressWarnings("WeakerAccess")
    public final void setSolution(ID s) {
        Objects.requireNonNull(s);
        Objects.requireNonNull(choices.get(s), "Opción '" + s + "' no añadida");

        setSolution( new SolutionBean<>(s) );
    }

    @Override
    public boolean equals(Object o) {
        QuestionChoice qc = (QuestionChoice) o;
        return getChoices().equals(qc.getChoices()) && super.equals(o);
    }
}
