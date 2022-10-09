package search;

import loader.UrlConstants;
import lombok.extern.slf4j.Slf4j;
import search.entity.entry.enties.vacancy.VacancyPage;

@Slf4j
public class VacancyPageSearch extends AbstractSearch<VacancyPage> {

    @Override
    public VacancyPage search() throws SearchException {
        try {
            putParametersToLoader(getParameterBox());
            log.info("Start loading content");
            String content = getLoader().loadContent(UrlConstants.VACANCIES_URL);
            log.info("Content loaded");
            return getParser().parse(content);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SearchException(e);
        }
    }
}