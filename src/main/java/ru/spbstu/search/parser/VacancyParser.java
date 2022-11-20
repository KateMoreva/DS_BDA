package ru.spbstu.search.parser;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;

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
