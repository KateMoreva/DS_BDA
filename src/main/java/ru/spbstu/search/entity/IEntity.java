package ru.spbstu.search.entity;

import ru.spbstu.search.entity.entry.IEntityEntry;

import java.util.List;
import java.util.Map;

public interface IEntity<T extends IEntityEntry> extends Iterable<T> {

    boolean hasId(String id);

    boolean hasName(String name);

    T getById(String id);

    T getByName(String name);

    void addEntry(T entry);

    int size();

    List<T> toList();

    Map<String, T> toIdMap();

    Map<String, T> toNameMap();

}
