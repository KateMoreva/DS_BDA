import lombok.extern.slf4j.Slf4j;
import search.ISearch;
import search.ISearchParam;
import search.SearchException;
import search.VacancySearch;
import search.entity.entry.enties.vacancy.VacancyList;

import java.util.List;

@Slf4j
public class HhApi {
    public static final int DEFAULT_VACANCY_LIMIT = 2;

    public static VacancyList search(int vacancyLimit, ISearchParam... searchParameters)
            throws SearchException {
        ISearch<VacancyList> search = new VacancySearch(vacancyLimit);
        for (ISearchParam parameter : searchParameters) {
            search.addParameter(parameter);
        }
        return search.search();
    }

    public static VacancyList search(ISearchParam... searchParameters) throws SearchException {
        return search(DEFAULT_VACANCY_LIMIT, searchParameters);
    }

    public static VacancyList search(List<ISearchParam> searchParameters) throws SearchException {
        ISearchParam[] paramArray = (ISearchParam[]) searchParameters.toArray();
        return search(DEFAULT_VACANCY_LIMIT, paramArray);
    }
}
