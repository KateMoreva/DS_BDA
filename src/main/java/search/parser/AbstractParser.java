package search.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import search.entity.entry.enties.profile.ProfFieldEntity;
import search.entity.entry.enties.vacancy.extra.Salary;
import search.entity.entry.enties.vacancy.extra.address.MetroCityEntity;
import search.entity.entry.enties.vacancy.extra.area.AreaEntity;
import search.parser.deserializer.*;

import java.util.Date;

@Slf4j
public abstract class AbstractParser<T> implements IParser<T> {
    private final Gson gson;

    protected AbstractParser() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Salary.class, new SalaryDeserializer())
                .registerTypeAdapter(MetroCityEntity.class, new MetroDeserializer())
                .registerTypeAdapter(AreaEntity.class, new AreaDeserializer())
                .registerTypeAdapter(ProfFieldEntity.class, new ProfFieldDeserializer());
        log.info("abstract parser {}", builder);
        gson = builder.create();
    }

    protected Gson getGson() {
        return gson;
    }
}