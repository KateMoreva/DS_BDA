package search;

public interface ISearchParam {
    SearchParameterBox getSearchParameters() throws SearchException;

    String getSearchParameterName();
}