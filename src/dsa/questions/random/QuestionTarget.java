package dsa.questions.random;

import dsa.questions.core.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionTarget<Q extends Question> extends SimpleTargetBean<Q> {
	private long ago;
	private List<Boolean> times = new ArrayList<>();

	public QuestionTarget(Q q) {
		super(q);
		ago = Long.MAX_VALUE;
	}

	public void addTimes(boolean ok) {
		times.add(ok);
	}

	public List<Boolean> getTimes() {
		return times;
	}

	@Override
	public void beforeOnPick() {
		ago = 0; // El siguiente no puede ser el mismo
	}

	public long getAgo() {
		return ago;
	}

	public void setAgo(long a) {
		ago = a;
	}

	@Override
	public void next() {
		if (ago != Long.MAX_VALUE)
			ago++;
	}
}
