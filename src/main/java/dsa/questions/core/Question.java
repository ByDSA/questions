package dsa.questions.core;

import dsa.questions.io.json.Idable;

import java.util.Objects;

public abstract class Question<S extends Solution<ANSWER>, ANSWER> implements Idable<Long> {
	private S solution;
	private Long id;

	public Question() {}

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

	@Override
	public boolean equals(Object o) {
		Question q = (Question) o;
		return (id == null && q.id == null || id != null && q.id != null && id.equals(q.id)) &&
				(getSolution() == null && q.getSolution() == null || getSolution() != null && q.getSolution() != null && getSolution().equals( q.getSolution()));
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long newId) {
		id = newId;
	}
}
