package ru.spbstu.search.parser;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;

@Slf4j
@Component
public class VacancyParser implements IParser<Vacancy> {

    private final Gson gson;

    @Autowired
    public VacancyParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Vacancy parse(String content) {
        return gson.fromJson(content, Vacancy.class);
    }
}
