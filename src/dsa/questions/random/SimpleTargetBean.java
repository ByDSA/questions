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
	public final T getValue() {
		return value;
	}

    @Override
	public final void setValue(T v) {
        Objects.requireNonNull(v);
	    value = v;
    }

	@SuppressWarnings("unchecked")
	@Override
	public SimpleTargetBean<T> pick(long dart) {
		return (SimpleTargetBean<T>) super.pick();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SimpleTargetBean<T> pick() {
		return (SimpleTargetBean<T>) super.pick();
	}

    @Override
    public String toString() {
        return value.toString();
    }
}
