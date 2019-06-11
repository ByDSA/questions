package dsa.questions.basic;

import dsa.questions.core.Question;

import java.util.*;

public class QuestionChoice<ID, ANSWER> extends Question<SolutionBean<ID>, ID> {
    private Map<ID, Choice<ID, ANSWER>> choices = new HashMap<>();
    private List<Choice<ID, ANSWER>> choicesList = new ArrayList<>();

    @SuppressWarnings("WeakerAccess")
    public QuestionChoice() {
    }

    @SuppressWarnings("WeakerAccess")
    public QuestionChoice(Choice<ID, ANSWER>[] choices, ID s) {
        Objects.requireNonNull(choices);
        Objects.requireNonNull(s);

        for (Choice<ID, ANSWER> c : choices)
            addChoice(c);

        setSolution(s);
    }

    @SuppressWarnings("WeakerAccess")
    public void addChoice(Choice<ID, ANSWER> c) {
        Objects.requireNonNull(c);

        choices.put(c.getId(), c);
        choicesList.add(c);
    }

    @SuppressWarnings("unused")
    public void addChoice(ID id, ANSWER a) {
        Choice<ID, ANSWER> c = new Choice<>(id, a);
        addChoice(c);
    }

    @SuppressWarnings("unused")
    public Choice<ID, ANSWER> getChoice(ID id) {
        Objects.requireNonNull(id);
        return choices.get(id);
    }

    @SuppressWarnings("WeakerAccess")
    public List<Choice<ID, ANSWER>> getChoices() {
        return choicesList;
    }

    @SuppressWarnings("unused")
    public List<Choice<ID, ANSWER>> getShuffledChoices() {
        List<Choice<ID, ANSWER>> l = getChoices();
        Collections.shuffle(l);

        return l;
    }

    @SuppressWarnings("WeakerAccess")
    public void setSolution(ID s) {
        Objects.requireNonNull(s);
        Objects.requireNonNull(choices.get(s), "Opción '" + s + "' no añadida");

        setSolution( new SolutionBean<>(s) );
    }
}
