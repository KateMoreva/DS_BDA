package ru.spbstu.search.entity.entry.enties.vacancy.extra.area;

import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.parser.AbstractParser;

@Slf4j
public final class AreasParser extends AbstractParser<AreaEntity> {
    @Override
    public AreaEntity parse(String content) {
        log.info("Start parsing area");
        return getGson().fromJson(content, AreaEntity.class);
    }
}
