package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.employer;

import java.ru.spbstu.search.ISearchParam;
import java.ru.spbstu.search.SearchException;
import java.ru.spbstu.search.SearchParamNames;
import java.ru.spbstu.search.SearchParameterBox;
import java.ru.spbstu.search.entity.entry.AbstractEntityEntry;

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
