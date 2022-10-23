package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import ru.spbstu.search.entity.entry.AbstractEntityEntry;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

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
