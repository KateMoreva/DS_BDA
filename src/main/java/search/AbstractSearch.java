package search;

import loader.ContentLoaderFactory;
import loader.IContentLoader;
import search.entity.entry.enties.vacancy.VacancyPage;
import search.parser.IParser;
import search.parser.VacanciesParser;

import java.util.List;
import java.util.Map;

public abstract class AbstractSearch<E> implements ISearch<E> {
    public static final int MAX_VACANCIES_LIMIT = 2000;
    public static final int MIN_VACANCIES_LIMIT = PerPage.MIN_PER_PAGE;
    public static final int DEFAULT_VACANCIES_LIMIT = 20;
    private final IParser<VacancyPage> parser = new VacanciesParser();
    private final IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();
    private final SearchParameterBox parameterBox = new SearchParameterBox();

    protected IParser<VacancyPage> getParser() {
        return parser;
    }

    protected IContentLoader getLoader() {
        return loader;
    }

    protected SearchParameterBox getParameterBox() {
        return parameterBox;
    }

    protected void putParametersToLoader(SearchParameterBox parameterBox) {
        Map<SearchParamNames, List<String>> paramMap = parameterBox.getParameterMap();
        for (SearchParamNames key : paramMap.keySet()) {
            for (String value : paramMap.get(key)) {
                loader.addParam(key.getName(), value);
            }
        }
    }

    @Override
    public AbstractSearch<E> addParameter(ISearchParam searchParameter) throws SearchException {
        parameterBox.addParameter(searchParameter.getSearchParameters());
        return this;
    }

    @Override
    public AbstractSearch<E> addParameter(SearchParameterBox parameterBox) throws SearchException {
        this.parameterBox.addParameter(parameterBox);
        return this;
    }
}
