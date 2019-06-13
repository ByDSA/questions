package dsa.questions.basic;

import dsa.questions.core.Solution;
import dsa.utils.Bean;

import java.util.Objects;

public class SolutionBean<T> implements Solution<T>, Bean<T> {
	private T value;

	@SuppressWarnings("unused")
	public SolutionBean() { } // Compatibilidad javabean

	@SuppressWarnings("WeakerAccess")
	public SolutionBean(T s) {
		setValue(s);
	}

	@Override
	public boolean match(T v) {
        if (v == null)
            return false;
		return v.equals(value);
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
}
