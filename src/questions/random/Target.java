package questions.random;

public interface Target {
	Target pick(long dart);
	Target pick();
	long surface();
	void next();
	void onPick();
	long getAgo();
	void setAgo(long a);
}
