package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import lombok.Getter;
import lombok.Setter;

import static java.lang.String.format;

public class Skill {
    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return format("%s", getName());
    }
}
