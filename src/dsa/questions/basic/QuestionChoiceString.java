package dsa.questions.basic;

public class QuestionChoiceString<ID> extends QuestionChoice<ID, String> {
	protected String question;

	public QuestionChoiceString() {
		super();
	}

	public QuestionChoiceString(String q, QuestionChoice.Choice<ID, String>[] cs, ID sol) {
		super(cs, sol);

		question = q;
	}

	@Override
	public String toString() {
		return question;
	}

	public String getQuestionString() {
		return question;
	}

	public void setQuestionString(String s) {
		question = s;
	}
}
