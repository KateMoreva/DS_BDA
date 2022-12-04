package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.area;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.ru.spbstu.search.ISearchParam;
import java.ru.spbstu.search.SearchException;
import java.ru.spbstu.search.SearchParamNames;
import java.ru.spbstu.search.SearchParameterBox;
import java.ru.spbstu.search.entity.IEntity;
import java.ru.spbstu.search.entity.entry.AbstractEntityEntry;

@Slf4j
public class Area extends AbstractEntityEntry implements ISearchParam {
    public static final Area NULL_AREA = new Area();
    @Getter
    @Setter
    private Area parent;

    @Getter
    @Setter
    private IEntity<Area> areas;


    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.AREA, getId());
    }

    @Override
    public String getSearchParameterName() {
        return "Area";
    }

}
