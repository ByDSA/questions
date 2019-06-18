package es.danisales.questions.io.json;

public interface Idable<T> {
    void setId(T newId);
    T getId();
}
