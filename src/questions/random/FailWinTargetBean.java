package questions.random;

import java.util.ArrayList;
import java.util.List;

public class FailWinTargetBean<T> extends SimpleTargetBean<T> {
	private List<Boolean> times = new ArrayList<>();
	
	public FailWinTargetBean(T v) {
		super(v);
	}

	public void addTimes(boolean ok) {
		times.add(ok);
	}

	public List<Boolean> getTimes() {
		return times;
	}
}
