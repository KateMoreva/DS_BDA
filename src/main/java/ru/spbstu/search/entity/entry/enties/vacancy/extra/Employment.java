package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import ru.spbstu.search.entity.entry.AbstractEntityEntry;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

public final class Employment extends AbstractEntityEntry implements ISearchParam {
    public static final Employment NULL_EMPLOYMENT = new Employment();

    private Employment() {
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.EMPLOYMENT, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.EMPLOYMENT.getName();
    }
}
