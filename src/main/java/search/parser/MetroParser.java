package search.parser;

import lombok.extern.slf4j.Slf4j;
import search.entity.entry.enties.vacancy.extra.address.MetroCityEntity;

@Slf4j
public final class MetroParser extends AbstractParser<MetroCityEntity> {
    @Override
    public MetroCityEntity parse(String content) {
        log.info("Start parsing metro");
        return getGson().fromJson(content, MetroCityEntity.class);
    }
}
