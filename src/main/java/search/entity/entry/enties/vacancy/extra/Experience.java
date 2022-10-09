package search.entity.entry.enties.vacancy.extra;

import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.entry.AbstractEntityEntry;

public final class Experience extends AbstractEntityEntry implements ISearchParam {
    public static final Experience NULL_EXPERIENCE = new Experience();

    private Experience() {
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.EXPERIENCE, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.EXPERIENCE.getName();
    }
}
