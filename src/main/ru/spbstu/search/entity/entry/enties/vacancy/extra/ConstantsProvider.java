package ru.spbstu.search.entity.entry.enties.vacancy.extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.search.entity.IEntity;

import javax.annotation.PostConstruct;

@Component
public class ConstantsProvider {


    private final ExtraInfoInitializer extraInfoInitializer;

    private Currency currency;
    private EducationLevel educationLevel;
    private Employment employment;
    private Experience experience;
    private Schedule schedule;
    private Label label;
//    private LanguageLevel languageLevel;
    private VacancyType vacancyType;
    private VacancySearchFields vacancySearchFields;
    private EmployerType employerType;
    private Order order;


    @Autowired
    public ConstantsProvider(ExtraInfoInitializer extraInfoInitializer) {
        this.extraInfoInitializer = extraInfoInitializer;
    }

    @PostConstruct
    public void init() {
        currency = new Currency();
        educationLevel = new EducationLevel();
        employment = new Employment();
        experience = new Experience();
        schedule = new Schedule();
        label = new Label();
//        languageLevel = new LanguageLevel();
        vacancyType = new VacancyType();
        vacancySearchFields = new VacancySearchFields();
        employerType = new EmployerType();
        order = new Order();
    }

    public ExtraInfoInitializer getExtraInfoInitializer() {
        return extraInfoInitializer;
    }

    public Currency getCurrency() {
        return currency;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public Employment getEmployment() {
        return employment;
    }

    public Experience getExperience() {
        return experience;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Label getLabel() {
        return label;
    }

//    public LanguageLevel getLanguageLevel() {
//        return languageLevel;
//    }

    public VacancyType getVacancyType() {
        return vacancyType;
    }

    public VacancySearchFields getVacancySearchFields() {
        return vacancySearchFields;
    }

    public EmployerType getEmployerType() {
        return employerType;
    }

    public Order getOrder() {
        return order;
    }

    /**
     * Валюта.
     *
     * @see Currency
     */
    public class Currency {
        public IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.Currency>
                CURRENCIES = extraInfoInitializer.getCurrency();
        /**
         * Рубли.
         */
        public ru.spbstu.search.entity.entry.enties.vacancy.extra.Currency
                RUR = CURRENCIES.getById("RUR");
        /**
         * Доллары.
         */
        public ru.spbstu.search.entity.entry.enties.vacancy.extra.Currency
                USD = CURRENCIES.getById("USD");
        /**
         * Евро.
         */
        public ru.spbstu.search.entity.entry.enties.vacancy.extra.Currency
                EUR = CURRENCIES.getById("EUR");

    }

    /**
     * Образование (резюме).
     *
     * @see EducationLevel
     */
    public class EducationLevel {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel>
                EDUCATION_LEVELS = extraInfoInitializer.getEducationLevel();
        /**
         * Высшее.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                HIGHER = EDUCATION_LEVELS.getById("higher");
        /**
         * Бакалавр.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                BACHELOR = EDUCATION_LEVELS.getById("bachelor");
        /**
         * Магистр.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                MASTER = EDUCATION_LEVELS.getById("master");
        /**
         * Кандидат наук.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                CANDIDATE = EDUCATION_LEVELS.getById("candidate");
        /**
         * Доктор наук.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                DOCTOR = EDUCATION_LEVELS.getById("doctor");
        /**
         * Неоконченное высшее.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                UNFINISHED_HIGHER = EDUCATION_LEVELS.getById("unfinished_higher");
        /**
         * Среднее.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                SECONDARY = EDUCATION_LEVELS.getById("secondary");
        /**
         * Среднее специальное.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.EducationLevel
                SPECIAL_SECONDARY = EDUCATION_LEVELS.getById("special_secondary");
    }

    /**
     * Тип занятости (вакансия).
     *
     * @see Employment
     */
    public class Employment {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment>
                EMPLOYMENTS = extraInfoInitializer.getEmployment();
        /**
         * Полная занятость.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment
                FULL = EMPLOYMENTS.getById("full");
        /**
         * Частичная занятость.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment
                PART = EMPLOYMENTS.getById("part");
        /**
         * Проектная работа.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment
                PROJECT = EMPLOYMENTS.getById("project");
        /**
         * Волонтерство.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment
                VOLUNTEER = EMPLOYMENTS.getById("volunteer");
        /**
         * Стажировка.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment
                PROBATION = EMPLOYMENTS.getById("probation");
    }

    /**
     * Опыт работы (вакансия).
     *
     * @see Experience
     */
    public class Experience {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.Experience>
                EXPERIENCES = extraInfoInitializer.getExperience();
        /**
         * Нет опыта.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Experience
                NO_EXPERIENCE = EXPERIENCES.getById("noExperience");
        /**
         * От 1 года до 3 лет.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Experience
                BETWEEN_1_AND_3 = EXPERIENCES.getById("between1And3");
        /**
         * От 3 до 6 лет.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Experience
                BETWEEN_3_AND_6 = EXPERIENCES.getById("between3And6");
        /**
         * Более 6 лет.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Experience
                MORE_THAN_6 = EXPERIENCES.getById("moreThan6");
    }

    /**
     * График работы.
     *
     * @see Schedule
     */
    public class Schedule {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule>
                SCHEDULES = extraInfoInitializer.getSchedule();
        /**
         * Полный день.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule
                FULL_DAY = SCHEDULES.getById("fullDay");
        /**
         * Сменный график.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule
                SHIFT = SCHEDULES.getById("shift");
        /**
         * Гибкий график.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule
                FLEXIBLE = SCHEDULES.getById("flexible");
        /**
         * Удаленная работа.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule
                REMOTE = SCHEDULES.getById("remote");
    }

    /**
     * Метки вакансии.
     *
     * @see Label
     */
    public class Label {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.Label>
                LABELS = extraInfoInitializer.getLabel();
        /**
         * Только с адресом.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Label
                WITH_ADDRESS = LABELS.getById("with_address");
        /**
         * Только доступные для людей с инвалидностью.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Label
                ACCEPT_HANDICAPPED = LABELS.getById("accept_handicapped");
        /**
         * Без вакансий агентств.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Label
                NOT_FROM_AGENCY = LABELS.getById("not_from_agency");

    }

    /**
     * Уровень владения языком
     *
     * @see LanguageLevel
     */
//    public class LanguageLevel {
//        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel>
//                LANGUAGE_LEVELS = extraInfoInitializer.getLanguageLevel();
//        /**
//         * Базовые знания.
//         */
//        ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel
//                BASIC = LANGUAGE_LEVELS.getById("basic");
//        /**
//         * Читаю профессиональную литературу.
//         */
//        ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel
//                CAN_READ = LANGUAGE_LEVELS.getById("can_read");
//        /**
//         * Могу проходить интервью.
//         */
//        ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel
//                CAN_PASS_INTERVIEW = LANGUAGE_LEVELS.getById("can_pass_interview");
//        /**
//         * Свободно владею.
//         */
//        ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel
//                FLUENT = LANGUAGE_LEVELS.getById("fluent");
//        /**
//         * Родной.
//         */
//        ru.spbstu.search.entity.entry.enties.vacancy.extra.LanguageLevel
//                NATIVE = LANGUAGE_LEVELS.getById("native");
//    }

    /**
     * Тип вакансии.
     *
     * @see VacancyType
     */
    public class VacancyType {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.VacancyType>
                VACANCY_TYPES = extraInfoInitializer.getVacancyType();
        /**
         * Открытая.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.VacancyType
                OPEN = VACANCY_TYPES.getById("open");
        /**
         * Закрытая.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.VacancyType
                CLOSED = VACANCY_TYPES.getById("closed");
        /**
         * Анонимная.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.VacancyType
                ANONYMOUS = VACANCY_TYPES.getById("anonymous");
        /**
         * С прямым откликом.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.VacancyType
                DIRECT = VACANCY_TYPES.getById("direct");
    }

    /**
     * Область поиска.
     *
     * @see VacancySearchFields
     */
    public class VacancySearchFields {
        IEntity<ru.spbstu.search.fields.VacancySearchFields>
                VACANCY_SEARCH_FIELDS = extraInfoInitializer.getVacancySearchFields();
        /**
         * В названии вакансии.
         */
        ru.spbstu.search.fields.VacancySearchFields
                VACANCY_NAME = VACANCY_SEARCH_FIELDS.getById("name");
        /**
         * В названии компании.
         */
        ru.spbstu.search.fields.VacancySearchFields
                COMPANY_NAME = VACANCY_SEARCH_FIELDS.getById("company_name");
        /**
         * В описании вакансии.
         */
        ru.spbstu.search.fields.VacancySearchFields
                DESCRIPTION = VACANCY_SEARCH_FIELDS.getById("description");
    }

    /**
     * Тип работодателя.
     */
    public class EmployerType {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerType>
                EMPLOYER_TYPES = extraInfoInitializer.getEmployerType();
        /**
         * Прямой работодатель.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerType
                COMPANY = EMPLOYER_TYPES.getById("company");
        /**
         * Кадровое агентство.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerType
                AGENCY = EMPLOYER_TYPES.getById("agency");
        /**
         * Частный специалист по подбору персонала.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerType
                PRIVATE_RECRUITER = EMPLOYER_TYPES.getById("private_recruiter");
    }

    /**
     * Сортировка списка вакансий.
     *
     * @see Order
     */
    public class Order {
        IEntity<ru.spbstu.search.entity.entry.enties.vacancy.extra.Order>
                ORDERS = extraInfoInitializer.getOrder();
        /**
         * По дате.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Order
                PUBLICATION_TIME = ORDERS.getById("publication_time");
        /**
         * По убыванию зарплаты.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Order
                SALARY_DESC = ORDERS.getById("salary_desc");
        /**
         * По возрастанию зарплаты.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Order
                SALARY_ASC = ORDERS.getById("salary_asc");
        /**
         * По соответствию.
         */
        ru.spbstu.search.entity.entry.enties.vacancy.extra.Order
                RELEVANCE = ORDERS.getById("relevance");
    }
}
