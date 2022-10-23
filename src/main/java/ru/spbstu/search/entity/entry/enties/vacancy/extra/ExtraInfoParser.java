package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.parser.AbstractParser;

@Slf4j
public class ExtraInfoParser extends AbstractParser<ExtraInfoEntityContainer> {
    @Override
    public ExtraInfoEntityContainer parse(String content) {
        log.info("Start passing external info {}", content);
        return getGson().fromJson(content, ExtraInfoEntityContainer.class);
    }
}
