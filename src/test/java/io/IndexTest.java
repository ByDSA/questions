package io;

import com.google.gson.Gson;
import dsa.questions.io.json.JsonManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndexTest extends IOTest {
    static final String folder = "test_io";

    @Test
    public void defaultValues() {
        JsonManager.IndexFile i = new JsonManager.IndexFile();
        assertNotNull(i);
        assertNotNull(i.getVersion());
        assertNotNull(i.getStats());
        assertNotNull(i.getQuestions());
        assertNotNull(i.getName());
    }

    @Test
    public void saveDefault() throws IOException {
        final String fileName = "testIndex.json";
        JsonManager.IndexFile i = new JsonManager.IndexFile();
        save(i, folder, fileName);

        JsonManager.IndexFile i2 = (JsonManager.IndexFile) load(JsonManager.IndexFile.class, folder, fileName);

        assertNotNull(i2);
        assertEquals(i.getName(), i2.getName());
        assertEquals(i.getQuestions(), i2.getQuestions());
        assertEquals(i.getStats(), i2.getStats());
        assertEquals(i.getVersion(), i2.getVersion());

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
