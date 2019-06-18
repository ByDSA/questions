package es.danisales.questions.core;

public interface Solution<ANSWER> {
	boolean match(ANSWER a);
}
