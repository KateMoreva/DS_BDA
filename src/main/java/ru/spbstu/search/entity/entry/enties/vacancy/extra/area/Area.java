package ru.spbstu.search.entity.entry.enties.vacancy.extra.area;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;
import ru.spbstu.search.entity.IEntity;
import ru.spbstu.search.entity.entry.AbstractEntityEntry;

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
