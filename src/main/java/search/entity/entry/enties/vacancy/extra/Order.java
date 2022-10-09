package search.entity.entry.enties.vacancy.extra;

import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.entry.AbstractEntityEntry;

public class Order extends AbstractEntityEntry implements ISearchParam {
    public static final Order NULL_ORDER = new Order();

    private Order() {
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.ORDER_BY, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.ORDER_BY.getName();
    }
}
