package ru.spbstu.loader;

import ru.spbstu.search.SearchException;

public interface IContentLoader {
    String loadContent(final String url) throws SearchException;

    void addHeader(String key, String value);

    void addParam(String key, String value);
}
