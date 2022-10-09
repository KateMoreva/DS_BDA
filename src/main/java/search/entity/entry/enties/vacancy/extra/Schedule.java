package search.entity.entry.enties.vacancy.extra;

import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.entry.AbstractEntityEntry;

public class Schedule extends AbstractEntityEntry implements ISearchParam {
    public static final Schedule NULL_SCHEDULE = new Schedule();

    private Schedule() {
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.SCHEDULE, getId());
    }

    @Override
    public String getSearchParameterName() {
        return "Schedule";
    }
}