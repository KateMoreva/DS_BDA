package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import ru.spbstu.search.entity.entry.AbstractEntityEntry;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

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
