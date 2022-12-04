package java.ru.spbstu.search.param;

import java.ru.spbstu.search.ISearchParam;
import java.ru.spbstu.search.SearchException;
import java.ru.spbstu.search.SearchParamNames;
import java.ru.spbstu.search.SearchParameterBox;

import static java.lang.String.format;

public final class Period implements ISearchParam {
    public static final int MIN_PERIOD = 1;
    public static final int MAX_PERIOD = 30;
    private Integer period;

    public Period(Integer period) throws SearchException {
        setPeriod(period);
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.PERIOD, String.valueOf(getPeriod()));
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) throws SearchException {
        if (period < MIN_PERIOD || period > MAX_PERIOD) {
            throw new SearchException(
                    format("Period %d is not in the allowed limit %d : %d ", period, MIN_PERIOD, MAX_PERIOD));
        }
        this.period = period;
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.PERIOD.getName();
    }
}
