package ru.spbstu.search.entity.entry.enties.profile;

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
public class ProfField extends AbstractEntityEntry implements ISearchParam {

    public static final ProfField NULL_PROF_FIELD = new ProfField();

    @Getter
    @Setter
    private IEntity<Specialization> specializations;

    @Override
    public SearchParameterBox getSearchParameters() throws SearchException {
        return new SearchParameterBox(SearchParamNames.PROF_FIELD, getId());
    }

    @Override
    public String getSearchParameterName() {
        return SearchParamNames.PROF_FIELD.getName();
    }
}
