package search.parser;

import lombok.extern.slf4j.Slf4j;
import search.entity.entry.enties.vacancy.extra.employer.EmployerSingle;

@Slf4j
final class EmployerParser extends AbstractParser<EmployerSingle> {
    @Override
    public EmployerSingle parse(String content) {
        log.info("Start parsing employer");
        return getGson().fromJson(content, EmployerSingle.class);
    }
}