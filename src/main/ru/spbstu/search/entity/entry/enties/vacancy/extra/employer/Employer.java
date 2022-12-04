package ru.spbstu.search.entity.entry.enties.vacancy.extra.employer;

import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;
import ru.spbstu.search.entity.entry.AbstractEntityEntry;

public class Employer extends AbstractEntityEntry implements ISearchParam {
    public Employer(String employerId) {
        setId(employerId);
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.EMPLOYER, getId());
    }

    @Override
    public String getSearchParameterName() {
        return "Employer";
    }
}
