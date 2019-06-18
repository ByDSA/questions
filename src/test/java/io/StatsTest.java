package io;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dsa.questions.io.json.JsonManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatsTest extends IOTest {
    static final String folder = "test_io";
    static final String fileName = "testStat.json";

    @Test
    public void defaultValues() {
        JsonManager.StatsFile i = new JsonManager.StatsFile();
        assertNotNull(i);
        assertNotNull(i.getContent());
        assertTrue(i.getContent().isEmpty());
    }

    @Test
    public void testValues() throws IOException {
        JsonManager.StatsFile i = new JsonManager.StatsFile();
        for (int j = 1; j <= 10; j++) {
            JsonManager.StatsFile.Stat s = new JsonManager.StatsFile.Stat();
            s.setAgo((long)Math.random()*10);
            s.setId(j);
            List<Boolean> times = new ArrayList<>();
            times.add(Math.random() < 0.5);
            s.setTimes(times);
        }

        save(i, folder, fileName);

        JsonManager.StatsFile i2 = (JsonManager.StatsFile) load(JsonManager.StatsFile.class, folder, fileName);

        assertNotNull(i2);
        assertEquals(i.getContent(), i2.getContent());

        delete(folder, fileName);

    }

    @Test
    public void saveDefault() throws IOException {
        JsonManager.StatsFile i = new JsonManager.StatsFile();
        save(i, folder, fileName);

        JsonManager.StatsFile i2 = (JsonManager.StatsFile) load(JsonManager.StatsFile.class, folder, fileName);

        assertNotNull(i2);
        assertEquals(i.getContent(), i2.getContent());

        delete(folder, fileName);
    }

    @BeforeClass
    public static void createFolder() {
        File f = new File(folder);
        f.mkdirs();
    }

    @AfterClass
    public static void deleteFolder() {
        File f = new File(folder);
        f.delete();
    }
}
