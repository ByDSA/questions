package questions.basic;

public class SolutionRegex extends SolutionBean<String> {
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
