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
    public Target pick(long dart) {
        return pick();
    }

    @Override
    public Target pick() {
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
