package search.entity.entry.enties.vacancy.extra.address;

import lombok.Getter;
import lombok.Setter;
import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.IEntity;
import search.entity.entry.AbstractEntityEntry;

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