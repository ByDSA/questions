package dsa.questions.core;

public class Question<S extends Solution<ANSWER>, ANSWER> implements java.io.Serializable {
	private S solution;

	public Question() {	}

	public Question(S s) {
		solution = s;
	}
	
	public boolean match(ANSWER a) {
		return solution.match(a);
	}
	
	public S getSolution() {
		return solution;
	}

	public void setSolution(S s) {
		solution = s;
	}
}
