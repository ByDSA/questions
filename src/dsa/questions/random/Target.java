package dsa.questions.random;

public interface Target {
	Target pickDart(long dart);
	Target pick();
	long getSurface();
	void next();
	void beforeOnPick();
	void afterOnPick();
}
