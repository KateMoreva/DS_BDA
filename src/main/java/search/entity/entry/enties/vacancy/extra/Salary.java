package search.entity.entry.enties.vacancy.extra;

import lombok.Data;
import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;

@Data
public class Salary implements ISearchParam {
    public static final Salary NULL_SALARY = new Salary();
    private static final Integer NULL_VALUE = -1;
    private Integer from;
    private Integer to;
    private Currency currency = Currency.NULL_CURRENCY;

    public Salary() {
        this(NULL_VALUE);
    }


    public Salary(Integer salary) {
        this(salary, salary, Currency.RUR);
    }


    public Salary(Integer from, Integer to, Currency currency) {
        setFrom(from);
        setTo(to);
        setCurrency(currency);
    }

    public static Salary toRur(Salary salary) {
        Salary salaryRur = new Salary();
        Currency currency = salary.getCurrency();

        Integer from = salary.getFrom();
        Integer fromRur = (from != null) ? new Double(from / currency.getRate()).intValue() : null;
        salaryRur.setFrom(fromRur);

        Integer to = salary.getTo();
        Integer toRur = (to != null) ? new Double(to / currency.getRate()).intValue() : null;
        salaryRur.setTo(toRur);

        salaryRur.setCurrency(Currency.RUR);

        return salaryRur;
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
