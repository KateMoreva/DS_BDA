package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import lombok.Getter;
import lombok.Setter;
import java.ru.spbstu.search.ISearchParam;
import java.ru.spbstu.search.SearchException;
import java.ru.spbstu.search.SearchParamNames;
import java.ru.spbstu.search.SearchParameterBox;
import java.ru.spbstu.search.entity.IEntity;
import java.ru.spbstu.search.entity.entry.AbstractEntityEntry;

public class MetroLine extends AbstractEntityEntry implements ISearchParam {
    public static final MetroLine NULL_METRO_LINE = new MetroLine();

    @Getter
    @Setter
    private String hexColor;
    @Getter
    @Setter
    private IEntity<MetroStation> stations;
    @Getter
    @Setter
    private MetroCity city;

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.METRO, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.METRO.getName();
    }

}
