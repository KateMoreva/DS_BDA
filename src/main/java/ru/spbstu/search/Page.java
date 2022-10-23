package ru.spbstu.search;

import static java.lang.String.format;

public final class Page implements ISearchParam {
    public static final int MIN_PAGE = 1;
    private Integer page;

    public Page(Integer page) throws SearchException {
        setPage(page);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) throws SearchException {
        if (page < MIN_PAGE) {
            throw new SearchException(
                    format("Wrong page number min %d, requested %d ", MIN_PAGE, page));
        }
        this.page = page;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.PAGE, String.valueOf(getPage()));
    }

    @Override
    public String getSearchParameterName() {
        return "Page";
    }

}
