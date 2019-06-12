package dsa.questions.random;

import dsa.questions.core.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionTarget<Q extends Question> extends SimpleTargetBean<Q> {
	private long ago;
	private List<Boolean> times = new ArrayList<>();

	@SuppressWarnings("WeakerAccess")
	public QuestionTarget(Q q) {
		super(q);
		ago = Long.MAX_VALUE;
	}

	@Override
	public QuestionTarget<Q> pick() {
		return (QuestionTarget<Q>) super.pick();
	}

	@Override
	public QuestionTarget<Q> pick(long dart) {
		return (QuestionTarget<Q>) super.pick(dart);
	}

	@SuppressWarnings("unused")
	public void addTimes(boolean ok) {
		times.add(ok);
	}

	public void clearTimes() {
		times.clear();
	}

	public void setTimes(List<Boolean> l) {
		times = l;
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

	@SuppressWarnings("unused")
	public void setAgo(long a) {
		ago = a;
	}

	@Override
	public void next() {
		if (ago != Long.MAX_VALUE)
			ago++;
	}
}
