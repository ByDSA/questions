import org.junit.Test;
import questions.random.PackTarget;
import questions.random.SimpleTarget;
import questions.utils.RandomUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PackTargetTests {
    @Test
    public void defaultConstructor() {
        PackTarget t = new PackTarget();
        assertTrue(t.getAgo() > 0);
    }

    @Test
    public void ago() {
        PackTarget t = new PackTarget();
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
        PackTarget t = new PackTarget();

        assertEquals(0, t.surface());
    }

    @Test(expected = PackTarget.EmptyException.class)
    public void defaultPickException() {
        PackTarget t = new PackTarget();

        t.pick(0);
    }

    @Test(expected = PackTarget.EmptyException.class)
    public void defaultPickException2() {
        PackTarget t = new PackTarget();

        t.pick();
    }

    @Test(expected = PackTarget.NoSurfaceException.class)
    public void surfaceException() {
        PackTarget t = new PackTarget();
        t.add(new SimpleTarget() {
            @Override
            public long surface() {
                return 0;
            }
        });

        t.pick();
    }

    @Test
    public void surfaceNoException() {
        PackTarget t = new PackTarget();
        t.add(new SimpleTarget() {
            @Override
            public long surface() {
                return -1;
            }
        });
        t.add(new SimpleTarget());

        t.pick();
    }

    @Test
    public void next() {
        PackTarget t = new PackTarget();

        assertEquals(Long.MAX_VALUE, t.getAgo());
        t.next();
        assertEquals(Long.MAX_VALUE, t.getAgo());
        t.add(new SimpleTarget());
        t.add(new SimpleTarget());
        t.pick();
        assertEquals(0, t.getAgo());
        t.next();
        assertEquals(1, t.getAgo());
        t.next();
        assertEquals(2, t.getAgo());
    }
}
