package ru.spbstu.search.parser;

import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyPage;

@Slf4j
public class VacanciesParser extends AbstractParser<VacancyPage> {
    @Override
    public VacancyPage parse(String content) {
        log.info("Start parsing vacancies");
        return getGson().fromJson(content, VacancyPage.class);
    }
}
