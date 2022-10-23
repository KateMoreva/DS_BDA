package ru.spbstu.search.entity.entry.enties.profile;

import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.parser.AbstractParser;

@Slf4j
public final class ProfFieldsParser extends AbstractParser<ProfFieldEntity> {
    @Override
    public ProfFieldEntity parse(String content) {
        log.info("Start parsing profile");
        return getGson().fromJson(content, ProfFieldEntity.class);
    }
}
