package dsa.questions.random;

public interface Target {
	Target pick(long dart);
	Target pick();
	long surface();
	void next();
	void beforeOnPick();
	void afterOnPick();
	long getAgo();
	void setAgo(long a);
}
