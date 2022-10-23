package ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import lombok.Getter;
import lombok.Setter;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;
import ru.spbstu.search.entity.IEntity;
import ru.spbstu.search.entity.entry.AbstractEntityEntry;

public class MetroLine extends AbstractEntityEntry implements ISearchParam {
    public static final MetroLine NULL_METRO_LINE = new MetroLine();
    public static final MetroLineEntity MOSCOW = MetroInitializer.getInstance().getMetroCities().getByName("Москва").getLines();
    public static final MetroLineEntity SAINT_PETERSBURG = MetroInitializer.getInstance().getMetroCities().getByName("Санкт-Петербург").getLines();

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
