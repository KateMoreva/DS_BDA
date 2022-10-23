package ru.spbstu.search.entity.entry.enties.profile;


import org.jetbrains.annotations.NotNull;

public class Specialization extends ProfField {
    public static final Specialization NULL_SPECIALIZATION = new Specialization();

    public Specialization(@NotNull String name) {
        this.setName(name);
    }

}
