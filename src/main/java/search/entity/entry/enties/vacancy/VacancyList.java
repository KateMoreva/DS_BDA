package search.entity.entry.enties.vacancy;

import java.util.ArrayList;
import java.util.List;

public class VacancyList extends ArrayList<Vacancy> {

    public VacancyList(List<Vacancy> vacancies) {
        addAll(vacancies);
    }

}
