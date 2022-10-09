package search.entity.entry.enties.vacancy.extra;

import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.entry.AbstractEntityEntry;

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
