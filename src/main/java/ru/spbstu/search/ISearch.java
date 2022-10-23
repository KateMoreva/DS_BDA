package ru.spbstu.search;

public interface ISearch<T> {
    T search() throws SearchException;

    ISearch<T> addParameter(ISearchParam parameter) throws SearchException;

    ISearch<T> addParameter(SearchParameterBox parameterBox) throws SearchException;
}
