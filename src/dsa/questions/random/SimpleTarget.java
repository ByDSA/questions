package dsa.questions.random;

public class SimpleTarget implements Target {
    public SimpleTarget() {
    }

    @Override
    public void beforeOnPick() {
    }

    @Override
    public void afterOnPick() {
    }

    @Override
    public SimpleTarget pick(long dart) {
        return pick();
    }

    @Override
    public SimpleTarget pick() {
        beforeOnPick();
        afterOnPick();
        return this;
    }

    @Override
    public long surface() {
        return 1;
    }

    @Override
    public void next() {
    }
}
