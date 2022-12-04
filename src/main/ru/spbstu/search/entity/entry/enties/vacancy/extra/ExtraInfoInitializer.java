package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.loader.IContentLoader;
import ru.spbstu.loader.UrlConstants;
import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.entity.Entity;
import ru.spbstu.search.entity.IEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyType;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerType;
import ru.spbstu.search.fields.VacancySearchFields;

import java.util.Collections;

@Slf4j
@Component
public class ExtraInfoInitializer {

    private final IContentLoader contentLoader;
    private final ExtraInfoParser extraInfoParser;

    private IEntity<Currency> currency;
    private IEntity<Schedule> schedule;
    private IEntity<EducationLevel> educationLevel;
    private IEntity<Employment> employment;
    private IEntity<Order> order;
    private IEntity<VacancySearchFields> vacancySearchFields;
    private IEntity<Experience> experience;
    private IEntity<EmployerType> employerType;
    private IEntity<Label> label;
    private IEntity<LanguageLevel> languageLevel;
    private IEntity<VacancyType> vacancyType;

    @Autowired
    private ExtraInfoInitializer(IContentLoader contentLoader) {
        this.contentLoader = contentLoader;
        this.extraInfoParser = new ExtraInfoParser(new GsonBuilder().create());
        log.debug("ExtraInfoInitializer");
        loadSmallDictionaries();
    }

    public IEntity<Currency> getCurrency() {
        return currency;
    }

    public IEntity<EmployerType> getEmployerType() {
        return employerType;
    }

    public IEntity<Schedule> getSchedule() {
        return schedule;
    }

    public IEntity<EducationLevel> getEducationLevel() {
        return educationLevel;
    }

    public IEntity<Employment> getEmployment() {
        return employment;
    }

    public IEntity<Order> getOrder() {
        return order;
    }

    public IEntity<VacancySearchFields> getVacancySearchFields() {
        return vacancySearchFields;
    }

    public IEntity<Experience> getExperience() {
        return experience;
    }

    public IEntity<Label> getLabel() {
        return label;
    }

    public IEntity<LanguageLevel> getLanguageLevel() {
        return languageLevel;
    }

    public IEntity<VacancyType> getVacancyType() {
        return vacancyType;
    }

    private void loadSmallDictionaries() {
        try {
            String content = contentLoader.loadContent(UrlConstants.DICTINARIES_URL, Collections.emptyMap());
            ExtraInfoEntityContainer extraInfoEntityContainer = extraInfoParser.parse(content);

            currency = new Entity<>(extraInfoEntityContainer.getCurrency(), Currency.NULL_CURRENCY);
            schedule = new Entity<>(extraInfoEntityContainer.getSchedule(), Schedule.NULL_SCHEDULE);
            educationLevel = new Entity<>(extraInfoEntityContainer.getEducationLevel(), EducationLevel.NULL_EDUCATION_LEVEL);
            employment = new Entity<>(extraInfoEntityContainer.getEmployment(), Employment.NULL_EMPLOYMENT);
            order = new Entity<>(extraInfoEntityContainer.getOrder(), Order.NULL_ORDER);
            vacancySearchFields = new Entity<>(extraInfoEntityContainer.getVacancySearchFields(), VacancySearchFields.NULL_VACANCY_SEARCH_FIELD);
            experience = new Entity<>(extraInfoEntityContainer.getExperience(), Experience.NULL_EXPERIENCE);
            employerType = new Entity<>(extraInfoEntityContainer.getEmployerType(), EmployerType.NULL_EMPLOYER_TYPE);
            label = new Entity<>(extraInfoEntityContainer.getLabel(), Label.NULL_LABEL);
            languageLevel = new Entity<>(extraInfoEntityContainer.getLanguageLevel(), LanguageLevel.NULL_LANGUAGE_LEVEL);
            vacancyType = new Entity<>(extraInfoEntityContainer.getVacancyType(), VacancyType.NULL_VACANCY_TYPE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
