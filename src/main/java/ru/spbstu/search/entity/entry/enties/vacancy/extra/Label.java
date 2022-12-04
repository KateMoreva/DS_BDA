package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import ru.spbstu.search.entity.entry.AbstractEntityEntry;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

public final class Label extends AbstractEntityEntry implements ISearchParam {
    public static final Label NULL_LABEL = new Label();

    private Label() {
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.LABEL, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.LABEL.getName();
    }
}
