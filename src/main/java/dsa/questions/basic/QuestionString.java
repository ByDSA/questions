package dsa.questions.basic;

import dsa.questions.core.Question;

import java.util.Objects;

@SuppressWarnings("unused")
public final class QuestionString extends Question<SolutionRegex, String> {
	private String questionString;

	@SuppressWarnings("WeakerAccess")
	public QuestionString(String q, SolutionRegex s) {
		super(s);

		Objects.requireNonNull(q);
		questionString = q;
	}

	@SuppressWarnings("unused")
	public QuestionString(String q, String s) {
		this(q, new SolutionRegex(s));
	}

	public boolean match(String str) {
		return super.match(str);
	}

	@SuppressWarnings("unused")
	public String getQuestionString() {
		return questionString;
	}

	@SuppressWarnings("unused")
	public void setQuestionString(String s) {
		questionString = s;
	}

	@SuppressWarnings("unused")
	public final void setSolution(String s) {
		super.setSolution( new SolutionRegex(s) );
	}

	@Override
	public String toString() {
		return questionString;
	}
}
