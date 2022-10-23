package ru.spbstu.search.parser;

import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;

@Slf4j
public class VacancyParser extends AbstractParser<Vacancy> {
    @Override
    public Vacancy parse(String content) {
        log.info("Start parsing vacancy");
        return getGson().fromJson(content, Vacancy.class);
    }
}
