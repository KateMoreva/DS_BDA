package search.param;


import lombok.extern.slf4j.Slf4j;
import search.ISearchParam;
import search.SearchException;
import search.SearchParamNames;
import search.SearchParameterBox;
import search.fields.VacancySearchFields;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Text implements ISearchParam {
    private String text = "";
    private VacancySearchFields[] fields = {};

    public Text(String text) {
        this.text = text;
    }

    public Text(String text, VacancySearchFields... fields) {
        this.text = text;
        this.fields = fields;
    }

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        try {
            SearchParameterBox params = new SearchParameterBox();
            params.addParameter(SearchParamNames.TEXT, URLEncoder.encode(text, StandardCharsets.UTF_8));
            for (VacancySearchFields field : fields) {
                params.addParameter(SearchParamNames.VACANCY_SEARCH_FIELDS, field.getId());
            }
            return params;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SearchException(e);
        }
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.TEXT.getName();
    }

}