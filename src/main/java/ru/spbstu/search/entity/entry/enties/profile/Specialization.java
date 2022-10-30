package ru.spbstu.search.entity.entry.enties.profile;


import org.jetbrains.annotations.NotNull;

public class Specialization extends ProfField {
    public static final Specialization NULL_SPECIALIZATION = new Specialization(null);

    public Specialization(String id) {
        this.setId(id);
    }

}
