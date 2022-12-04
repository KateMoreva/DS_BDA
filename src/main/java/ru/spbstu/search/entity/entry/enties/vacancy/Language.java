package java.ru.spbstu.search.entity.entry.enties.vacancy;

import lombok.Getter;
import lombok.Setter;
import java.ru.spbstu.search.entity.entry.AbstractEntityEntry;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel;

public class Language extends AbstractEntityEntry {
    @Getter
    @Setter
    LanguageLevel level;
}
