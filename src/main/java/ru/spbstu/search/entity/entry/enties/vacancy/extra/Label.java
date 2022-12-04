package java.ru.spbstu.search.entity.entry.enties.vacancy.extra;

import java.ru.spbstu.search.entity.entry.AbstractEntityEntry;
import java.ru.spbstu.search.ISearchParam;
import java.ru.spbstu.search.SearchException;
import java.ru.spbstu.search.SearchParamNames;
import java.ru.spbstu.search.SearchParameterBox;

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
