package dsa.questions.random;

public class SimpleTarget implements Target {
    private long surface = 1;

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
    public long getSurface() {
        return surface;
    }

    public void setSurface(long s) {
        surface = s;
    }

    @Override
    public void next() {
    }
}
