package io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import dsa.questions.basic.*;
import dsa.questions.core.Question;
import dsa.questions.io.json.JsonManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class QuestionTest extends IOTest {
    static final String folder = "test_io";
    static final String fileName = "questions.json";

    @Test
    public void defaultValues() {
        JsonManager.QuestionsFile i = new JsonManager.QuestionsFile();
        assertNotNull(i);
        assertNotNull(i.getContent());
    }

    @Test
    public void saveDefault() throws IOException {
        JsonManager.QuestionsFile i = new JsonManager.QuestionsFile();
        save(i, folder, fileName);

        JsonManager.QuestionsFile i2 = (JsonManager.QuestionsFile) load(JsonManager.QuestionsFile.class, folder, fileName);

        assertNotNull(i2);
        assertEquals(i.getContent(), i2.getContent());

        delete(folder, fileName);
    }

    @Test
    public void questionStringJson() {
        QuestionString q = new QuestionString("question", "solution");
        Gson gson = JsonManager.gsonBuilder.setPrettyPrinting().create();
        String jsonStr = gson.toJson(q);
        QuestionString q2 = gson.fromJson(jsonStr, QuestionString.class);
        assertNotNull(q2);
        assertEquals(q.getQuestionString(), q2.getQuestionString());
        assertEquals(q.getSolution().getValue(), q2.getSolution().getValue());
        assertEquals(q.getSolution(), q2.getSolution());
        assertEquals(q, q2);
    }

    @Test
    public void questionStringJsonFromGeneralQuestion() {
        QuestionString q = new QuestionString("question", "solution");
        Gson gson = JsonManager.gsonBuilder.setPrettyPrinting().create();
        String jsonStr = gson.toJson(q);
        QuestionString q2 = (QuestionString)gson.fromJson(jsonStr, Question.class);
        assertNotNull(q2);
        assertEquals(q.getQuestionString(), q2.getQuestionString());
        assertEquals(q.getSolution().getValue(), q2.getSolution().getValue());
        assertEquals(q.getSolution(), q2.getSolution());
        assertEquals(q, q2);
    }

    @Test
    public void questionChoiceStringJson() {
        // Create instance
        ChoiceString[] cs = new ChoiceString[] {
                new ChoiceString("a", "Opción A"),
                new ChoiceString("b", "Opción B"),
                new ChoiceString("c", "Opción C"),
                new ChoiceString("d", "Opción D"),
        };
        QuestionChoiceStringString q = new QuestionChoiceStringString("question", cs, "a");

        // Saving
        Gson gson = JsonManager.gsonBuilder.setPrettyPrinting().create();
        String jsonStr = gson.toJson(q);

        // Reading
        QuestionChoiceStringString q2 = gson.fromJson(jsonStr, q.getClass());

        // Asserts
        assertNotNull(q2);
        assertEquals(q.getQuestionString(), q2.getQuestionString());
        assertEquals(q.getSolution().getValue(), q2.getSolution().getValue());
        assertEquals(q.getSolution(), q2.getSolution());

        for (int i = 0; i < q.getChoices().size(); i++) {
            assertEquals(q.getChoices().get(i).getAnswer(), q2.getChoices().get(i).getAnswer());
            assertEquals(q.getChoices().get(i).getId(), q2.getChoices().get(i).getId());
            assertEquals(q.getChoices().get(i), q2.getChoices().get(i));
        }

        assertEquals(q, q2);
    }

    @Test
    public void questionChoiceStringJsonFromGeneralQuestion() {
        // Create instance
        ChoiceString[] cs = new ChoiceString[] {
                new ChoiceString("a", "Opción A"),
                new ChoiceString("b", "Opción B"),
                new ChoiceString("c", "Opción C"),
                new ChoiceString("d", "Opción D"),
        };
        QuestionChoiceStringString q = new QuestionChoiceStringString("question", cs, "a");

        // Saving
        Gson gson = JsonManager.gsonBuilder.setPrettyPrinting().create();
        String jsonStr = gson.toJson(q);

        // Loading
        QuestionChoiceStringString q2 = (QuestionChoiceStringString)gson.fromJson(jsonStr, Question.class);

        // Asserts
        assertNotNull(q2);
        assertEquals(q.getQuestionString(), q2.getQuestionString());
        assertEquals(q.getSolution().getValue(), q2.getSolution().getValue());
        assertEquals(q.getSolution(), q2.getSolution());

        for (int i = 0; i < q.getChoices().size(); i++) {
            assertEquals(q.getChoices().get(i).getAnswer(), q2.getChoices().get(i).getAnswer());
            assertEquals(q.getChoices().get(i).getId(), q2.getChoices().get(i).getId());
            assertEquals(q.getChoices().get(i), q2.getChoices().get(i));
        }

        assertEquals(q, q2);
    }

    @Test
    public void testValues() throws IOException {
        JsonManager.QuestionsFile i = new JsonManager.QuestionsFile();
        List<Question> content = new ArrayList<>();
        Question q = new QuestionString("question", "solution");
        content.add(q);
        ChoiceString[] cs = new ChoiceString[] {
                new ChoiceString("a", "Opción A"),
                new ChoiceString("b", "Opción B"),
                new ChoiceString("c", "Opción C"),
                new ChoiceString("d", "Opción D"),
        };
        q = new QuestionChoiceStringString("question", cs, "a");
        content.add(q);
        i.setContent(content);
        Type t = new TypeToken<List<Question>>(){}.getType();
        save(i, folder, fileName);

        JsonManager.QuestionsFile i2 = (JsonManager.QuestionsFile) load(JsonManager.QuestionsFile.class, folder, fileName);

        assertNotNull(i2);
        for (int j = 0; j < i.getContent().size(); j++) {
            assertEquals(i.getContent().get(j), i2.getContent().get(j));
        }
        assertEquals(i.getContent(), i2.getContent());
        assertEquals(i, i2);

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

    public void save(Object i, String folder, String path) throws IOException {
        Gson gson = JsonManager.gsonBuilder.setPrettyPrinting().create();
        try (Writer writer = new FileWriter(folder + "/" + path)) {
            gson.toJson(i, writer);
        }
    }

    public Object load(Class clz, String folder, String path) throws IOException {
        Gson gson = JsonManager.gsonBuilder.setPrettyPrinting().create();
        try (Reader reader = new FileReader(folder + "/" + path)) {
            return gson.fromJson(reader, clz);
        }
    }
}
