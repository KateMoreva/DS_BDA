package java.ru.spbstu.search;

public interface ISearchParam {
    SearchParameterBox getSearchParameters() throws SearchException;

    String getSearchParameterName();
}
