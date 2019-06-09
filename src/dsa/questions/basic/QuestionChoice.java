package dsa.questions.basic;

import dsa.questions.core.Question;

import java.util.*;

public class QuestionChoice<ID, ANSWER> extends Question<SolutionBean<ID>, ID> {
    private Map<ID, ANSWER> options = new HashMap<>();

    public QuestionChoice() {
    }

    public QuestionChoice(Choice<ID, ANSWER>[] choices, ID s) {
        assert choices != null;

        for (Choice c : choices)
            addOption(c);

        setSolution(s);
    }

    public void addOption(Choice<ID, ANSWER> c) {
        options.put((ID)c.id, (ANSWER)c.answer);
    }

    public List<Choice<ID, ANSWER>> getChoices() {
        List<Choice<ID, ANSWER>> l = new ArrayList<>();
        for (Map.Entry<ID, ANSWER> entry : options.entrySet()) {
            Choice<ID, ANSWER> c = new Choice<ID, ANSWER>(entry.getKey(), entry.getValue());
            l.add(c);
        }

        return l;
    }

    public List<Choice<ID, ANSWER>> getShuffledChoices() {
        List<Choice<ID, ANSWER>> l = getChoices();
        Collections.shuffle(l);

        return l;
    }

    public void setSolution(ID s) {
        if (options.get(s) == null)
            throw new OptionNotAddedExeption(s);
        setSolution( new SolutionBean<ID>(s) );
    }

    public static class Choice<ID, ANSWER> {
        private ID id;
        private ANSWER answer;

        public Choice(ID id, ANSWER a) {
            this.id = id;
            answer = a;
        }

        public ID getId() {
            return id;
        }

        public ANSWER getAnswer() {
            return answer;
        }
    }

    public static class OptionNotAddedExeption extends RuntimeException {
        public OptionNotAddedExeption(Object o) {
            super("La opción " + o + " no ha sido añadida.");
        }
    }
}
