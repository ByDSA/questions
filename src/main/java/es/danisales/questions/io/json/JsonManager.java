package es.danisales.questions.io.json;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import es.danisales.questions.basic.*;
import es.danisales.questions.core.Question;
import es.danisales.random.PackQuestionTarget;
import es.danisales.random.QuestionTarget;
import es.danisales.questions.basic.ChoiceString;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public abstract class JsonManager {
    String folder;
    static String questionsFolder = "questions";

    IndexFile indexFile = new IndexFile();
    static IdManager<Long, Question> idManager = new IdManager<>();

    static final Map<Class, BiMap<Class,String>> superToSubMap = new HashMap();

    public static final GsonBuilder gsonBuilder = new GsonBuilder();

    static final String CLASS_META_KEY = "type";

    private static void writeType(JsonObject jsonQuestion, Class superClass, Type typeOfSrc) {
        String typeStr = superToSubMap.get(superClass).get(typeOfSrc);
        if (typeStr == null)
            throw new JsonParseException("'" + CLASS_META_KEY + "' parameter for '" + typeOfSrc + "' is unregistred.");
        jsonQuestion.addProperty(CLASS_META_KEY, typeStr);
    }

    static JsonSerializer<QuestionChoiceStringString> questionChoiceStringSerializer = new JsonSerializer<QuestionChoiceStringString>() {
        @Override
        public JsonElement serialize(QuestionChoiceStringString src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonQuestion = new JsonObject();
            jsonQuestion.addProperty("id", idManager.getId(src));
            writeType(jsonQuestion, Question.class, typeOfSrc);
            jsonQuestion.addProperty("question", src.getQuestionString());
            jsonQuestion.addProperty("answer", src.getSolution().getValue());
            JsonArray choicesJson = new JsonArray();
            for (ChoiceString c : src.getChoices()) {
                choicesJson.add(context.serialize(c));
            }
            jsonQuestion.add("choices", choicesJson);

            return jsonQuestion;
        }
    };

    private static String readString(JsonObject jsonObject, String par) {
        JsonElement questionJson = jsonObject.get(par);
        if (questionJson == null)
            throw new JsonParseException("'" + par + "' parameter expected.");

        return questionJson.getAsString();
    }

    private static Long readLong(JsonObject jsonObject, String par) {
        JsonElement questionJson = jsonObject.get(par);
        if (questionJson == null)
            throw new JsonParseException("'" + par + "' parameter expected.");

        return questionJson.getAsLong();
    }

    public static void readId(JsonObject jsonObject, Question q) {
        try {
            Long id = readLong(jsonObject, "id");
            if (id != null)
                q.setId(id);
        } catch(JsonParseException e) { }
    }

    static JsonDeserializer<QuestionChoiceStringString> questionChoiceStringDeserializer = new JsonDeserializer<QuestionChoiceStringString>() {
        @Override
        public QuestionChoiceStringString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            String question = readString(jsonObject, "question");
            String answer = readString(jsonObject, "answer");
            JsonElement choicesJson = jsonObject.get("choices");
            ChoiceString[] cs = context.deserialize(choicesJson, ChoiceString[].class);

            QuestionChoiceStringString q = new QuestionChoiceStringString(question, cs, answer);

            readId(jsonObject, q);

            return q;
        }
    };

    static JsonSerializer choiceStringStringSerializer = new JsonSerializer<ChoiceString>() {
        @Override
        public JsonElement serialize(ChoiceString src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject choiceJson = new JsonObject();
            choiceJson.addProperty("id", src.getId());
            writeType(choiceJson, Choice.class, typeOfSrc);
            choiceJson.addProperty("text", src.getAnswer());

            return choiceJson;
        }
    };

    static JsonDeserializer<ChoiceString> choiceStringStringDeserializer = new JsonDeserializer<ChoiceString>() {
        @Override
        public ChoiceString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            String id = jsonObject.get("id").getAsString();
            String answer = jsonObject.get("text").getAsString();

            return new ChoiceString(id, answer);
        }
    };

    static JsonSerializer<QuestionString> questionStringSerializer = new JsonSerializer<QuestionString>() {
        @Override
        public JsonElement serialize(QuestionString src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonQuestion = new JsonObject();
            jsonQuestion.addProperty("id", idManager.getId(src));
            writeType(jsonQuestion, Question.class, typeOfSrc);
            jsonQuestion.addProperty("question", src.getQuestionString());
            jsonQuestion.addProperty("answer", src.getSolution().getValue());

            return jsonQuestion;
        }
    };

    static JsonDeserializer<QuestionString> questionStringDeserializer = new JsonDeserializer<QuestionString>() {
        @Override
        public QuestionString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            String question = jsonObject.get("question").getAsString();
            String answer = jsonObject.get("answer").getAsString();

            QuestionString q = new QuestionString(question, answer);
            readId(jsonObject, q);

            return q;
        }
    };

    static JsonDeserializer<Question> questionDeserializer = new JsonDeserializer<Question>() {
        @Override
        public Question deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement typeElement = jsonObject.get("type");
            if (typeElement == null)
                throw new JsonParseException("'type' parameter expected.");
            String typeStr = typeElement.getAsString();

            Class subType = superToSubMap.get(Question.class).inverse().get(typeStr);
            if (subType == null)
                throw new JsonParseException("Unregistred type '" + typeStr + "'.");
            return context.deserialize(json, subType);
        }
    };

    static {
        addClass(Question.class, "choice-str", QuestionChoiceStringString.class, questionChoiceStringSerializer, questionChoiceStringDeserializer);
        addClass(Question.class, "str", QuestionString.class, questionStringSerializer, questionStringDeserializer);
        addClass(Choice.class, "str", ChoiceString.class, choiceStringStringSerializer, choiceStringStringDeserializer);

        addSerializer(Question.class, null, questionDeserializer);
    }

    static void addSerializer(Class t, JsonSerializer s, JsonDeserializer d) {
        if (s != null)
            gsonBuilder.registerTypeAdapter(t, s);
        if (d != null)
            gsonBuilder.registerTypeAdapter(t, d);
    }

    public static void addClass(Class superClass, String name, Class qClass, JsonSerializer s, JsonDeserializer d) {
        BiMap<Class, String> m = superToSubMap.get(superClass);
        if (m == null)
            m = HashBiMap.create();
        m.put(qClass, name);
        superToSubMap.put(superClass, m);

        addSerializer(qClass, s, d);
    }

    public static void setQuestionsFolder(String f) {
        questionsFolder = f;
    }

    @SuppressWarnings("WeakerAccess")
    public void load(String fname) {
        folder = questionsFolder + "/" + fname;

        // IndexFile
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(folder + "/index.json"));
            indexFile = gson.fromJson(reader, IndexFile.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Index file not found");
        }

        try {
            reader = new JsonReader(new FileReader(folder + "/" + indexFile.questions));
            QuestionsFile questionsFile = gson.fromJson(reader, QuestionsFile.class);
            for (Question q : questionsFile.content) {
                Long id = q.getId();
                if (id == null) {
                    id = idManager.add(q);
                    q.setId(id);
                } else
                    idManager.add(id, q);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Questions file not found");
        }
    }

    public String getFolder() {
        return folder;
    }

    public List<Question> getQuestions() {
        return idManager.getAllInstances();
    }

    public void loadStats(PackQuestionTarget p) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;
        StatsFile statsFile;
        reader = new JsonReader(new FileReader(folder + "/" + indexFile.stats));
        statsFile = gson.fromJson(reader, StatsFile.class);

        for (StatsFile.Stat s : statsFile.content) {
            Question q = idManager.getInstance(s.id);
            if (q == null)
                continue;
            QuestionTarget t = p.getTarget(q);
            if (t == null)
                throw new NullPointerException(q.toString());
            t.setAgo(s.ago);
            t.setTimes(s.times);
        }
    }

    public void saveStats(PackQuestionTarget p) throws IOException {
        StatsFile statsFile = new StatsFile();

        for (Question q : idManager.getAllInstances()) {
            StatsFile.Stat s = new StatsFile.Stat();
            s.id = idManager.getId(q);
            s.ago = p.getTarget(q).getAgo();
            s.times = (List<Boolean>)p.getTarget(q).getTimes();
            statsFile.content.add(s);
        }

        Gson gson = new Gson();
        gson.toJson(statsFile, new FileWriter(folder + "/" + indexFile.stats));
    }

    public static class IndexFile {
        private String version = "1.0";
        private String questions = "questions.json";
        private String name = "Unknown";
        private String stats = "stats.json";

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getQuestions() {
            return questions;
        }

        public void setQuestions(String questions) {
            this.questions = questions;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStats() {
            return stats;
        }

        public void setStats(String stats) {
            this.stats = stats;
        }
    }

    public static class StatsFile {
        private List<Stat> content;

        public StatsFile() {
            this.content = new ArrayList<>();
        }

        public List<Stat> getContent() {
            return content;
        }

        public void setContent(List<Stat> content) {
            this.content = content;
        }

        public static class Stat {
            private long id;
            private long ago;
            private List<Boolean> times;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getAgo() {
                return ago;
            }

            public void setAgo(long ago) {
                this.ago = ago;
            }

            public List<Boolean> getTimes() {
                return times;
            }

            public void setTimes(List<Boolean> times) {
                this.times = times;
            }
        }
    }

    public static class QuestionsFile {
        private List<Question> content;

        public QuestionsFile() {
            this.content = new ArrayList<>();
        }

        public List<Question> getContent() {
            return content;
        }

        public void setContent(List<Question> content) {
            this.content = content;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof QuestionsFile) {
                QuestionsFile qf = (QuestionsFile)o;

                if (content.size() != qf.content.size())
                    return false;

                for (int i = 0; i < content.size(); i++)
                    if (!content.get(i).equals(qf.content.get(i)))
                        return false;

                return true;
            }

            return false;
        }
    }
}
