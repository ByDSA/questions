import dsa.questions.random.SimpleTarget;
import dsa.questions.utils.RandomUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleTargetTests {
    @Test
    public void pick() {
        SimpleTarget t = new SimpleTarget();

        int SIZE = 1000;
        long[] randomArray = RandomUtils.generateRandomLongArray(SIZE);

        for (int i = 0; i < SIZE; i++) {
            assertEquals(t, t.pickDart(randomArray[i]));
            assertEquals(t, t.pick());
        }
    }

    @Test
    public void defaultSurface() {
        SimpleTarget t = new SimpleTarget();

        assertEquals(1, t.getSurface());
        t.pickDart(0);
        t.pickDart(1);
        t.beforeOnPick();
        assertEquals(1, t.getSurface());
    }
}
