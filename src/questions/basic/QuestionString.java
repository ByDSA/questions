package questions.basic;

import questions.core.Question;
import questions.random.Target;

public class QuestionString<S extends SolutionRegex, T extends Target> extends Question<S, String> {
	private String question;

	public QuestionString(String q, S s) {
		super(s);

		question = q;
	}

	public QuestionString(String q, String s) {
		this(q, (S) new SolutionRegex(s));
	}

	@Override
	public String toString() {
		return question;
	}

	public boolean match(String str) {
		return super.match(str);
	}

	public String getQuestionString() {
		return question;
	}

	public void setQuestionString(String s) {
		question = s;
	}
	
	public void setSolution(String s) {
		super.setSolution( (S)new SolutionRegex(s) );
	}
}
