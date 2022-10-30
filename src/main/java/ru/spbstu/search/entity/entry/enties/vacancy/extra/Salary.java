package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import lombok.Data;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;

@Data
public class Salary implements ISearchParam {
    public static final Salary NULL_SALARY = new Salary();
    private static final Integer NULL_VALUE = -1;
    private Integer from;
    private Integer to;
    private Currency currency = Currency.NULL_CURRENCY;
    private Boolean gross;

    public Salary() {
    }

    public Salary(Integer from, Integer to, Currency currency) {
        setFrom(from);
        setTo(to);
        setCurrency(currency);
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        SearchParameterBox params = new SearchParameterBox();
        if (from != null) {
            params.addParameter(SearchParamNames.SALARY, String.valueOf(from));
        } else {
            if (to != null) {
                params.addParameter(SearchParamNames.SALARY, String.valueOf(to));
            } else {
                throw new SearchException("No min or max salary mentioned");
            }
        }
        params.setParameter(SearchParamNames.CURRENCY, getCurrency().getId());
        return params;
    }

    public boolean isNull() {
        return ((getTo() == null && getFrom() == null) || NULL_VALUE.equals(getTo()) || NULL_VALUE.equals(getFrom()));
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.CURRENCY.getName();
    }
}
