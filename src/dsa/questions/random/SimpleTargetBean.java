package dsa.questions.random;

import dsa.questions.utils.Bean;

import java.util.Objects;

public class SimpleTargetBean<T> extends SimpleTarget implements Bean<T> {
	private T value;

	@SuppressWarnings("WeakerAccess")
	public SimpleTargetBean(T v) {
		super();

		setValue(v);
	}

	@Override
	public Target pick(long dart) {
		return this;
	}

	@Override
	public T getValue() {
		return value;
	}

    @Override
	public void setValue(T v) {
        Objects.requireNonNull(v);
	    value = v;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
