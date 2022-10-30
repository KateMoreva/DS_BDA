package ru.spbstu.search.parser;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroCityEntity;

@Slf4j
@Component
public final class MetroParser implements IParser<MetroCityEntity> {

    private final Gson gson;

    @Autowired
    public MetroParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public MetroCityEntity parse(String content) {
        log.info("Start parsing metro");
        return gson.fromJson(content, MetroCityEntity.class);
    }
}
