package es.danisales.questions.io.json;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

public class IdManager<ID, T> {
    BiMap<ID, T> idQuestion = HashBiMap.create();
    List<T> allInstances = new ArrayList();

    public void add(ID id, T q) {
        ID idGet = idQuestion.inverse().get(q);
        T qGet = idQuestion.get(id);
        if (idGet != null || qGet != null && qGet.equals(q))
            return;

        if (qGet != null && !qGet.equals(q))
            throw new RuntimeException("Ya existe id=" + id);
        idQuestion.put(id, q);
        allInstances.add(q);
    }

    public ID add(T t) {
        allInstances.add(t);

        ID id = generateId();

        return id;
    }

    public ID generateId() {
        return null;
    }

    public ID getId(T q) {
        ID ret = idQuestion.inverse().get(q);

        return ret;
    }

    public T getInstance(long id) {
        T ret = idQuestion.get(id);

        return ret;
    }

    public void addAll(List<T> l) {
        for (T t : l)
            add(t);
    }

    public List<T> getAllInstances() {
        return allInstances;
    }
}
