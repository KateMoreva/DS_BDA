package ru.spbstu.search;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.loader.IContentLoader;
import ru.spbstu.loader.UrlConstants;
import ru.spbstu.search.entity.entry.enties.profile.Specialization;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyPage;
import ru.spbstu.search.parser.IParser;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;

import static java.lang.String.format;

@Slf4j
@Component
public class SearchComponent {

    private static final Map<String, List<String>> EMPTY_PARAMS = Collections.emptyMap();

    private final IContentLoader contentLoader;
    private final IParser<VacancyPage> vacancyPageParser;
    private final IParser<Vacancy> vacancyParser;
    private List<String> missedIds = new CopyOnWriteArrayList<>();

    @Autowired
    public SearchComponent(IContentLoader contentLoader,
                           IParser<VacancyPage> vacancyPageParser,
                           IParser<Vacancy> vacancyParser) {
        this.contentLoader = contentLoader;
        this.vacancyPageParser = vacancyPageParser;
        this.vacancyParser = vacancyParser;
    }

    public VacancyPage search(@NotNull SearchParameterBox parameterBox) throws SearchException {
        try {
            Map<String, List<String>> params = SearchUtil.mapToUrlParams(parameterBox);
            String content = contentLoader.loadContent(UrlConstants.VACANCIES_URL, params);
            VacancyPage page = vacancyPageParser.parse(content);
            if (CollectionUtils.isEmpty(page.getItems())) {
                return page;
            }
            return prepareVacancies(page);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SearchException(e);
        }
    }

    public Vacancy search(@NotNull String vacancyId) throws SearchException {
        try {
            String content = contentLoader.loadContent(format(UrlConstants.VACANCY_URL, vacancyId));
            if (content == null) {
                return null;
            }
            Gson gson = new Gson();
            Vacancy vacancy =  gson.fromJson(content, Vacancy.class);
            for (Specialization specialization : vacancy.getSpecializations()) {
                if (specialization.getProfarea_id() == 1) {
                    return vacancy;
                }
            }
            log.info("Vacancy [{}] not for 1 area : [{}]", vacancy.getId(), vacancy.getName());
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
            throw new SearchException(e);
        }
        return null;
    }

    private VacancyPage prepareVacancies(@NotNull VacancyPage vacancyPage) throws SearchException {
        List<Vacancy> vacancyList = vacancyPage.getItems();
        for (Vacancy vacancy : vacancyList) {
            String content = contentLoader.loadContent(format(UrlConstants.VACANCY_URL, vacancy.getId()), EMPTY_PARAMS);
            Vacancy vacancyWithFullInfo = vacancyParser.parse(content);
            vacancy.setDescription(vacancyWithFullInfo.getDescription());
            vacancy.setSchedule(vacancyWithFullInfo.getSchedule());
            vacancy.setAcceptHandicapped(vacancyWithFullInfo.getAcceptHandicapped());
            vacancy.setExperience(vacancyWithFullInfo.getExperience());
            vacancy.setEmployment(vacancyWithFullInfo.getEmployment());
            vacancy.setArchived(vacancyWithFullInfo.getArchived());
            vacancy.setProfFields(vacancyWithFullInfo.getProfFields());
            vacancy.setKeySkills(vacancyWithFullInfo.getKeySkills());
            vacancy.setProfessionalRoles(vacancyWithFullInfo.getProfessionalRoles());
        }
        return vacancyPage;

    }

}
