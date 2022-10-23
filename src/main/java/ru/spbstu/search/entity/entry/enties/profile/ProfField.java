package ru.spbstu.search.entity.entry.enties.profile;

import ru.spbstu.loader.ContentLoaderFactory;
import ru.spbstu.loader.IContentLoader;
import ru.spbstu.loader.UrlConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.ISearchParam;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParamNames;
import ru.spbstu.search.SearchParameterBox;
import ru.spbstu.search.entity.IEntity;
import ru.spbstu.search.entity.entry.AbstractEntityEntry;
import ru.spbstu.search.parser.IParser;

@Slf4j
public class ProfField extends AbstractEntityEntry implements ISearchParam {

    public static final ProfField NULL_PROF_FIELD = new ProfField();

    public static final ProfFieldEntity PROF_FIELDS = loadProfFields();
    @Getter
    @Setter
    private IEntity<Specialization> specializations;

    private static ProfFieldEntity loadProfFields() {
        ProfFieldEntity profFields;
        try {
            log.debug("Try load profile");
            IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();
            String content = loader.loadContent(UrlConstants.SPECIALIZATIONS_URL);
            IParser<ProfFieldEntity> parse = new ProfFieldsParser();
            profFields = parse.parse(content);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            profFields = new ProfFieldEntity();
        }
        return profFields;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.PROF_FIELD, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.PROF_FIELD.getName();
    }
}
