package search.entity.entry.enties.vacancy.extra.area;

import loader.ContentLoaderFactory;
import loader.IContentLoader;
import loader.UrlConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.entity.IEntity;
import search.entity.entry.AbstractEntityEntry;
import search.parser.IParser;

@Slf4j
public class Area extends AbstractEntityEntry implements ISearchParam {
    public static final Area NULL_AREA = new Area();
    public static final AreaEntity AREAS = loadAreas();
    public static final Area MOSCOW = AREAS.getByName("Москва");
    public static final Area SAINT_PETERSBURG = AREAS.getByName("Санкт-Петербург");
    @Getter
    @Setter
    private Area parent;

    @Getter
    @Setter
    private IEntity<Area> areas;

    private static AreaEntity loadAreas() {
        AreaEntity areas;
        try {
            log.debug("load Areas.");
            IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();
            String content = loader.loadContent(UrlConstants.AREAS_URL);
            IParser<AreaEntity> parse = new AreasParser();
            areas = parse.parse(content);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            areas = new AreaEntity();
        }
        return areas;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.AREA, getId());
    }

    @Override
    public String getSearchParameterName() {
        return "Area";
    }

}
