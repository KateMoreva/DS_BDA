package ru.spbstu.search.param;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

@Getter
public class DateTo implements ISearchParam {

    private final String to;

    public DateTo(@NotNull String to) {
        this.to = to;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.DATE_TO, to);
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.DATE_TO.getName();
    }
}
