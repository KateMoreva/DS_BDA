package ru.spbstu.search.parser;

public interface IParser<T> {
    T parse(String content);
}
