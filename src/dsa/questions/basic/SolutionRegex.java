package dsa.questions.basic;

public final class SolutionRegex extends SolutionBean<String> {
    @SuppressWarnings("WeakerAccess")
	public SolutionRegex(String s) {
		super(s);
	}
	
	@Override
	public boolean match(String str) {
		return str.matches(getValue());
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
