package dsa.questions.basic;

import dsa.questions.core.Solution;

public class SolutionBean<T> implements Solution<T> {
	private T value;

	public SolutionBean() { }

	public SolutionBean(T s) {
		value = s;
	}

	@Override
	public boolean match(T v) {
		return v.equals(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T v) {
		value = v;
	}
}
