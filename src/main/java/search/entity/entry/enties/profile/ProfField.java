package search.entity.entry.enties.profile;

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