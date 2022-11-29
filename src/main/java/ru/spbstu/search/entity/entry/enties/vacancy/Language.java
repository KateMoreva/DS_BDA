package ru.spbstu.search.entity.entry.enties.vacancy;

import lombok.Getter;
import lombok.Setter;
import ru.spbstu.search.entity.entry.AbstractEntityEntry;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel;

public class Language extends AbstractEntityEntry {
    @Getter
    @Setter
    LanguageLevel level;
}
