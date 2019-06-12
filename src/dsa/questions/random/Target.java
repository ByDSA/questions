package dsa.questions.random;

public interface Target {
	Target pick(long dart);
	Target pick();
	long getSurface();
	void next();
	void beforeOnPick();
	void afterOnPick();
}
