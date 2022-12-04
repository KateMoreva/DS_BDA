package java.ru.spbstu.search.entity.entry.enties.vacancy.extra;

import java.ru.spbstu.search.entity.entry.AbstractEntityEntry;
import java.ru.spbstu.search.ISearchParam;
import java.ru.spbstu.search.SearchException;
import java.ru.spbstu.search.SearchParamNames;
import java.ru.spbstu.search.SearchParameterBox;

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
