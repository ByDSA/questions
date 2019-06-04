package questions.random;

import questions.core.Question;
import questions.core.Solution;
import questions.random.FailWinTargetBean;

public class QuestionTarget<Q extends Question<S, A>, S extends Solution<A>, A> extends FailWinTargetBean<Q> {
	public QuestionTarget(Q q) {
		super(q);
	}

	public QuestionTarget(Q q, S s) {
		super(q);

		q.setSolution(s);
	}
	
	public String toString() {
		return getValue().toString();
	}
	
	public S getSolution() {
		return getValue().getSolution();
	}
}
