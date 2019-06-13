package dsa.questions.core;

import java.util.Objects;

public class Question<S extends Solution<ANSWER>, ANSWER> {
	private S solution;

	public Question() {	}

	public Question(S s) {
		setSolution(s);
	}
	
	public boolean match(ANSWER a) {
		return solution.match(a);
	}
	
	public S getSolution() {
		return solution;
	}

	public void setSolution(S s) {
		Objects.requireNonNull(s);
		solution = s;
	}
}
