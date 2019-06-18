package io;

import com.google.gson.Gson;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IOTest {
    public void delete(String folder, String path) {
        File f = new File(folder + "/" + path);
        if (!f.delete())
            throw new RuntimeException();
    }

    public void save(Object i, String folder, String path) throws IOException {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(folder + "/" + path)) {
            gson.toJson(i, writer);
        }
    }

    public Object load(Class clz, String folder, String path) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(folder + "/" + path)) {
            return gson.fromJson(reader, clz);
        }
    }
}
