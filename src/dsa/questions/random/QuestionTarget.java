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
	public QuestionTarget<Q> pickDart(long dart) {
		return (QuestionTarget<Q>) super.pickDart(dart);
	}

	@SuppressWarnings("unused")
	public final void addTimes(boolean ok) {
		times.add(ok);
	}

	@SuppressWarnings("unused")
	public final void clearTimes() {
		times.clear();
	}

	@SuppressWarnings("unused")
	public final void setTimes(List<Boolean> l) {
		times = l;
	}

	public final List<Boolean> getTimes() {
		return times;
	}

	@Override
	public void beforeOnPick() {
		ago = 0; // El siguiente no puede ser el mismo
	}

	public final long getAgo() {
		return ago;
	}

	@SuppressWarnings("unused")
	public final void setAgo(long a) {
		ago = a;
	}

	@Override
	public void next() {
		if (ago != Long.MAX_VALUE)
			ago++;
	}
}
