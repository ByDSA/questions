import org.junit.Test;
import questions.random.SimpleTarget;
import questions.utils.RandomUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleTargetTests {
    @Test
    public void defaultConstructor() {
        SimpleTarget t = new SimpleTarget();
        assertTrue(t.getAgo() > 0);
    }

    @Test
    public void ago() {
        SimpleTarget t = new SimpleTarget();
        assertEquals(Long.MAX_VALUE, t.getAgo());
        t.setAgo(1);
        assertEquals(1, t.getAgo());
        t.setAgo(10);
        assertEquals(10, t.getAgo());
    }

    @Test
    public void pick() {
        SimpleTarget t = new SimpleTarget();

        int SIZE = 1000;
        long[] randomArray = RandomUtils.generateRandomLongArray(SIZE);

        for (int i = 0; i < SIZE; i++) {
            assertEquals(t, t.pick(randomArray[i]));
            assertEquals(t, t.pick());
        }
    }

    @Test
    public void defaultSurface() {
        SimpleTarget t = new SimpleTarget();

        assertEquals(1, t.surface());
        t.pick(0);
        t.pick(1);
        t.onPick();
        assertEquals(1, t.surface());
    }

    @Test
    public void next() {
        SimpleTarget t = new SimpleTarget();

        assertEquals(Long.MAX_VALUE, t.getAgo());
        t.next();
        assertEquals(Long.MAX_VALUE, t.getAgo());
        t.pick();
        assertEquals(0, t.getAgo());
        t.next();
        assertEquals(1, t.getAgo());
        t.next();
        assertEquals(2, t.getAgo());
    }
}
