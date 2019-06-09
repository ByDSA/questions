package dsa.questions.random;

public class SimpleTargetBean<T> extends SimpleTarget {
	protected T value;

	public SimpleTargetBean(T v) {
		super();

		value = v;
	}

	@Override
	public Target pick(long dart) {
		return this;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
	public T getValue() {
		return value;
	}
}
