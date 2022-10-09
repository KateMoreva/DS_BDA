package search.entity.entry.enties.vacancy.extra;

import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.entry.AbstractEntityEntry;

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
