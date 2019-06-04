import java.util.Calendar;

import org.junit.Test;
import questions.core.Question;
import questions.core.Solution;

import static org.junit.Assert.*;

public class QuestionTests {
	final static Solution SOLUTION_EXAMPLE = new Solution<String>() {
		@Override
		public boolean match(String a) {
			return a.equals("success");
		}
	};

	@Test
	public void defaultConstructor() {
		Question q = new Question();

		assertNull(q.getSolution());
	}

	@Test
	public void solutionConstructor() {
		assertTrue(SOLUTION_EXAMPLE.match("success"));
		assertFalse(SOLUTION_EXAMPLE.match("failure"));
		Question q = new Question(SOLUTION_EXAMPLE);

		assertEquals(SOLUTION_EXAMPLE, q.getSolution());
		assertNotNull(q.getSolution());
		assertTrue(q.match("success"));
		assertFalse(q.match("failure"));
	}

	@Test
	public void setSolution() {
		Question q = new Question();

		q.setSolution(SOLUTION_EXAMPLE);
		assertTrue(q.match("success"));
		assertFalse(q.match("failure"));
	}
}
