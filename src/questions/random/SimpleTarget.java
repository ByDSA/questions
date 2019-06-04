package questions.random;

public class SimpleTarget implements Target {
    private long ago;

    public SimpleTarget() {
        ago = Long.MAX_VALUE;
    }

    @Override
    public void onPick() {
        ago = 0; // El siguiente no puede ser el mismo
    }

    @Override
    public Target pick(long dart) {
        return pick();
    }

    @Override
    public Target pick() {
        onPick();
        return this;
    }

    @Override
    public long surface() {
        return 1;
    }

    @Override
    public void next() {
        if (ago != Long.MAX_VALUE)
            ago++;
    }

    @Override
    public long getAgo() {
        return ago;
    }

    public void setAgo(long a) {
        ago = a;
    }
}
