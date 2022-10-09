package search.entity.entry.enties.vacancy.extra;

import search.entity.IEntity;

public interface Constants {

    /**
     * Валюта.
     *
     * @see Currency
     */
    public static interface Currency {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.Currency>
                CURRENCIES = ExtraInfoInitializer.getInstance().getCurrency();
        /**
         * Рубли.
         */
        public static final search.entity.entry.enties.vacancy.extra.Currency
                RUR = CURRENCIES.getById("RUR");
        /**
         * Доллары.
         */
        public static final search.entity.entry.enties.vacancy.extra.Currency
                USD = CURRENCIES.getById("USD");
        /**
         * Евро.
         */
        public static final search.entity.entry.enties.vacancy.extra.Currency
                EUR = CURRENCIES.getById("EUR");
    }

    /**
     * Образование (резюме).
     *
     * @see EducationLevel
     */
    public static interface EducationLevel {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.EducationLevel>
                EDUCATION_LEVELS = ExtraInfoInitializer.getInstance().getEducationLevel();
        /**
         * Высшее.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                HIGHER = EDUCATION_LEVELS.getById("higher");
        /**
         * Бакалавр.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                BACHELOR = EDUCATION_LEVELS.getById("bachelor");
        /**
         * Магистр.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                MASTER = EDUCATION_LEVELS.getById("master");
        /**
         * Кандидат наук.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                CANDIDATE = EDUCATION_LEVELS.getById("candidate");
        /**
         * Доктор наук.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                DOCTOR = EDUCATION_LEVELS.getById("doctor");
        /**
         * Неоконченное высшее.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                UNFINISHED_HIGHER = EDUCATION_LEVELS.getById("unfinished_higher");
        /**
         * Среднее.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                SECONDARY = EDUCATION_LEVELS.getById("secondary");
        /**
         * Среднее специальное.
         */
        public static final search.entity.entry.enties.vacancy.extra.EducationLevel
                SPECIAL_SECONDARY = EDUCATION_LEVELS.getById("special_secondary");
    }

    /**
     * Тип занятости (вакансия).
     *
     * @see Employment
     */
    public static interface Employment {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.Employment>
                EMPLOYMENTS = ExtraInfoInitializer.getInstance().getEmployment();
        /**
         * Полная занятость.
         */
        public static final search.entity.entry.enties.vacancy.extra.Employment
                FULL = EMPLOYMENTS.getById("full");
        /**
         * Частичная занятость.
         */
        public static final search.entity.entry.enties.vacancy.extra.Employment
                PART = EMPLOYMENTS.getById("part");
        /**
         * Проектная работа.
         */
        public static final search.entity.entry.enties.vacancy.extra.Employment
                PROJECT = EMPLOYMENTS.getById("project");
        /**
         * Волонтерство.
         */
        public static final search.entity.entry.enties.vacancy.extra.Employment
                VOLUNTEER = EMPLOYMENTS.getById("volunteer");
        /**
         * Стажировка.
         */
        public static final search.entity.entry.enties.vacancy.extra.Employment
                PROBATION = EMPLOYMENTS.getById("probation");
    }

    /**
     * Опыт работы (вакансия).
     *
     * @see Experience
     */
    public static interface Experience {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.Experience>
                EXPERIENCES = ExtraInfoInitializer.getInstance().getExperience();
        /**
         * Нет опыта.
         */
        public static final search.entity.entry.enties.vacancy.extra.Experience
                NO_EXPERIENCE = EXPERIENCES.getById("noExperience");
        /**
         * От 1 года до 3 лет.
         */
        public static final search.entity.entry.enties.vacancy.extra.Experience
                BETWEEN_1_AND_3 = EXPERIENCES.getById("between1And3");
        /**
         * От 3 до 6 лет.
         */
        public static final search.entity.entry.enties.vacancy.extra.Experience
                BETWEEN_3_AND_6 = EXPERIENCES.getById("between3And6");
        /**
         * Более 6 лет.
         */
        public static final search.entity.entry.enties.vacancy.extra.Experience
                MORE_THAN_6 = EXPERIENCES.getById("moreThan6");
    }

    /**
     * График работы.
     *
     * @see Schedule
     */
    public static interface Schedule {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.Schedule>
                SCHEDULES = ExtraInfoInitializer.getInstance().getSchedule();
        /**
         * Полный день.
         */
        public static final search.entity.entry.enties.vacancy.extra.Schedule
                FULL_DAY = SCHEDULES.getById("fullDay");
        /**
         * Сменный график.
         */
        public static final search.entity.entry.enties.vacancy.extra.Schedule
                SHIFT = SCHEDULES.getById("shift");
        /**
         * Гибкий график.
         */
        public static final search.entity.entry.enties.vacancy.extra.Schedule
                FLEXIBLE = SCHEDULES.getById("flexible");
        /**
         * Удаленная работа.
         */
        public static final search.entity.entry.enties.vacancy.extra.Schedule
                REMOTE = SCHEDULES.getById("remote");
    }

    /**
     * Метки вакансии.
     *
     * @see Label
     */
    public static interface Label {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.Label>
                LABELS = ExtraInfoInitializer.getInstance().getLabel();
        /**
         * Только с адресом.
         */
        public static final search.entity.entry.enties.vacancy.extra.Label
                WITH_ADDRESS = LABELS.getById("with_address");
        /**
         * Только доступные для людей с инвалидностью.
         */
        public static final search.entity.entry.enties.vacancy.extra.Label
                ACCEPT_HANDICAPPED = LABELS.getById("accept_handicapped");
        /**
         * Без вакансий агентств.
         */
        public static final search.entity.entry.enties.vacancy.extra.Label
                NOT_FROM_AGENCY = LABELS.getById("not_from_agency");

    }

    /**
     * Уровень владения языком
     *
     * @see LanguageLevel
     */
    public static interface LanguageLevel {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.LanguageLevel>
                LANGUAGE_LEVELS = ExtraInfoInitializer.getInstance().getLanguageLevel();
        /**
         * Базовые знания.
         */
        public static final search.entity.entry.enties.vacancy.extra.LanguageLevel
                BASIC = LANGUAGE_LEVELS.getById("basic");
        /**
         * Читаю профессиональную литературу.
         */
        public static final search.entity.entry.enties.vacancy.extra.LanguageLevel
                CAN_READ = LANGUAGE_LEVELS.getById("can_read");
        /**
         * Могу проходить интервью.
         */
        public static final search.entity.entry.enties.vacancy.extra.LanguageLevel
                CAN_PASS_INTERVIEW = LANGUAGE_LEVELS.getById("can_pass_interview");
        /**
         * Свободно владею.
         */
        public static final search.entity.entry.enties.vacancy.extra.LanguageLevel
                FLUENT = LANGUAGE_LEVELS.getById("fluent");
        /**
         * Родной.
         */
        public static final search.entity.entry.enties.vacancy.extra.LanguageLevel
                NATIVE = LANGUAGE_LEVELS.getById("native");
    }

    /**
     * Тип вакансии.
     *
     * @see VacancyType
     */
    public static interface VacancyType {
        public static final IEntity<search.entity.entry.enties.vacancy.VacancyType>
                VACANCY_TYPES = ExtraInfoInitializer.getInstance().getVacancyType();
        /**
         * Открытая.
         */
        public static final search.entity.entry.enties.vacancy.VacancyType
                OPEN = VACANCY_TYPES.getById("open");
        /**
         * Закрытая.
         */
        public static final search.entity.entry.enties.vacancy.VacancyType
                CLOSED = VACANCY_TYPES.getById("closed");
        /**
         * Анонимная.
         */
        public static final search.entity.entry.enties.vacancy.VacancyType
                ANONYMOUS = VACANCY_TYPES.getById("anonymous");
        /**
         * С прямым откликом.
         */
        public static final search.entity.entry.enties.vacancy.VacancyType
                DIRECT = VACANCY_TYPES.getById("direct");
    }

    /**
     * Область поиска.
     *
     * @see VacancySearchFields
     */
    public static interface VacancySearchFields {
        public static final IEntity<search.fields.VacancySearchFields>
                VACANCY_SEARCH_FIELDS = ExtraInfoInitializer.getInstance().getVacancySearchFields();
        /**
         * В названии вакансии.
         */
        public static final search.fields.VacancySearchFields
                VACANCY_NAME = VACANCY_SEARCH_FIELDS.getById("name");
        /**
         * В названии компании.
         */
        public static final search.fields.VacancySearchFields
                COMPANY_NAME = VACANCY_SEARCH_FIELDS.getById("company_name");
        /**
         * В описании вакансии.
         */
        public static final search.fields.VacancySearchFields
                DESCRIPTION = VACANCY_SEARCH_FIELDS.getById("description");
    }

    /**
     * Тип работодателя.
     */
    public static interface EmployerType {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.employer.EmployerType>
                EMPLOYER_TYPES = ExtraInfoInitializer.getInstance().getEmployerType();
        /**
         * Прямой работодатель.
         */
        public static final search.entity.entry.enties.vacancy.extra.employer.EmployerType
                COMPANY = EMPLOYER_TYPES.getById("company");
        /**
         * Кадровое агентство.
         */
        public static final search.entity.entry.enties.vacancy.extra.employer.EmployerType
                AGENCY = EMPLOYER_TYPES.getById("agency");
        /**
         * Частный специалист по подбору персонала.
         */
        public static final search.entity.entry.enties.vacancy.extra.employer.EmployerType
                PRIVATE_RECRUITER = EMPLOYER_TYPES.getById("private_recruiter");
    }

    /**
     * Сортировка списка вакансий.
     *
     * @see Order
     */
    public static interface Order {
        public static final IEntity<search.entity.entry.enties.vacancy.extra.Order>
                ORDERS = ExtraInfoInitializer.getInstance().getOrder();
        /**
         * По дате.
         */
        public static final search.entity.entry.enties.vacancy.extra.Order
                PUBLICATION_TIME = ORDERS.getById("publication_time");
        /**
         * По убыванию зарплаты.
         */
        public static final search.entity.entry.enties.vacancy.extra.Order
                SALARY_DESC = ORDERS.getById("salary_desc");
        /**
         * По возрастанию зарплаты.
         */
        public static final search.entity.entry.enties.vacancy.extra.Order
                SALARY_ASC = ORDERS.getById("salary_asc");
        /**
         * По соответствию.
         */
        public static final search.entity.entry.enties.vacancy.extra.Order
                RELEVANCE = ORDERS.getById("relevance");
    }
}
