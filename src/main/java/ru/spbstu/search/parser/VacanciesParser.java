package ru.spbstu.search.parser;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyPage;

@Slf4j
@Component
public class VacanciesParser implements IParser<VacancyPage> {

    private final Gson gson;

    @Autowired
    public VacanciesParser(Gson gson) {
        this.gson = gson;
    }


    @Override
    public VacancyPage parse(String content) {
        return gson.fromJson(content, VacancyPage.class);
    }
}
