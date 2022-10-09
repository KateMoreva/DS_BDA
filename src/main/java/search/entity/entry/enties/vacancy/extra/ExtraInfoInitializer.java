package search.entity.entry.enties.vacancy.extra;

import loader.ContentLoaderFactory;
import loader.IContentLoader;
import loader.UrlConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import search.entity.Entity;
import search.entity.IEntity;
import search.entity.entry.enties.vacancy.VacancyType;
import search.entity.entry.enties.vacancy.extra.employer.EmployerType;
import search.fields.VacancySearchFields;

@Slf4j
public class ExtraInfoInitializer {
    private static ExtraInfoInitializer entity;
    private final IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();
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

    private ExtraInfoInitializer() {
        log.debug("ExtraInfoInitializer");
        loadSmallDictionaries();
    }

    public static synchronized ExtraInfoInitializer getInstance() {
        if (entity == null) {
            entity = new ExtraInfoInitializer();
        }
        return entity;
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
            String content = loader.loadContent(UrlConstants.DICTINARIES_URL);
            ExtraInfoParser parse = new ExtraInfoParser();
            ExtraInfoEntityContainer extraInfoEntityContainer = parse.parse(content);

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
