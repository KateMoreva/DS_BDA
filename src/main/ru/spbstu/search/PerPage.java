package ru.spbstu.search;

import static java.lang.String.format;

public final class PerPage implements ISearchParam {
    public static final int MIN_PER_PAGE = 1;
    public static final int MAX_PER_PAGE = 500;
    private Integer perPage;

    public PerPage(Integer perPage) throws SearchException {
        setPerPage(perPage);
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) throws SearchException {
        if (perPage < MIN_PER_PAGE || perPage > MAX_PER_PAGE) {
            throw new SearchException(
                    format("Wrong number of elements: min %d, max %d, requested %d",
                            MIN_PER_PAGE, MAX_PER_PAGE, perPage));
        }
        this.perPage = perPage;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.PER_PAGE, String.valueOf(getPerPage()));
    }

    @Override
    public String getSearchParameterName() {
        return "Per page";
    }
}
