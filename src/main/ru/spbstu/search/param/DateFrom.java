package ru.spbstu.search.param;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

@Getter
public class DateFrom implements ISearchParam {

    private final String from;

    public DateFrom(@NotNull String from) {
        this.from = from;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.DATE_FROM, from);
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.DATE_FROM.getName();
    }
}
