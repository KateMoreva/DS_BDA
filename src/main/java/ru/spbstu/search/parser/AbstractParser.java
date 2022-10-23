package ru.spbstu.search.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.spbstu.search.entity.entry.enties.profile.ProfFieldEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroCityEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.AreaEntity;
import ru.spbstu.search.parser.deserializer.*;

import java.util.Date;

public abstract class AbstractParser<T> implements IParser<T> {
    private final Gson gson;

    protected AbstractParser() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Salary.class, new SalaryDeserializer())
                .registerTypeAdapter(MetroCityEntity.class, new MetroDeserializer())
                .registerTypeAdapter(AreaEntity.class, new AreaDeserializer())
                .registerTypeAdapter(ProfFieldEntity.class, new ProfFieldDeserializer());
        gson = builder.create();
    }

    protected Gson getGson() {
        return gson;
    }
}
