package search.entity.entry.enties.vacancy.extra.employer;

import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.entry.AbstractEntityEntry;

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
