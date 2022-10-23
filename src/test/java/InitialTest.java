import ru.spbstu.HhApi;
import ru.spbstu.loader.ContentLoaderFactory;
import ru.spbstu.loader.UrlConstants;
import org.junit.jupiter.api.Test;
import ru.spbstu.search.Page;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.VacancyPageSearch;
import ru.spbstu.search.entity.IEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyList;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyPage;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.Area;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.AreaEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.AreasParser;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.Employer;
import ru.spbstu.search.param.Text;
import ru.spbstu.search.parser.IParser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InitialTest {
    @Test
    public void helloWorld() throws SearchException {
        VacancyList vacancies = HhApi.search(new Text("Java"));
        for (Vacancy vacancy : vacancies) {
            System.out.printf("%s (%s)\n", vacancy.getName(), vacancy.getArea());
        }
    }


    @Test
    public void areas() throws Exception {
        String content = ContentLoaderFactory.newInstanceLongTermCache().loadContent(UrlConstants.AREAS_URL);
        IParser<AreaEntity> parser = new AreasParser();
        IEntity<Area> areas = parser.parse(content);
        System.out.println(areas);
        assertNotNull(areas);
        assertEquals(9, areas.size());
    }

    @Test
    public void employerInVacancyParse() throws SearchException {
        final Employer bspb = new Employer("3783");
        System.out.println("Employer: " + bspb);
        VacancyList vacancies = HhApi.search(bspb);
        for (Vacancy vacancy : vacancies) {
            System.out.println("Vacancy: " + vacancy);
            assertEquals(vacancy.getEmployer(), bspb);
        }
    }

    @Test
    public void testPage() throws SearchException {
        VacancyPage vacancyPage1 = new VacancyPageSearch().addParameter(new Page(1)).search();
        int pages = vacancyPage1.getPages();
        int perPage = vacancyPage1.getPerPage();
        int itemsCount = vacancyPage1.getItems().size();

        for (int page = 1; page <= 5; page++) {
            VacancyPage vacancyPage = new VacancyPageSearch().addParameter(new Page(page)).search();
            assertThat(vacancyPage.getPage(), is(page));
            assertThat(vacancyPage.getPages(), is(pages));
            assertThat(vacancyPage.getPerPage(), is(perPage));
            assertThat(vacancyPage.getItems(), hasSize(itemsCount));
        }
    }
}
