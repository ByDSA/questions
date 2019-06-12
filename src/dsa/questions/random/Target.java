package dsa.questions.random;

public interface Target {
	<T extends Target> T pick(long dart);

	<T extends Target> T pick();
	long surface();
	void next();
	void beforeOnPick();
	void afterOnPick();
}
