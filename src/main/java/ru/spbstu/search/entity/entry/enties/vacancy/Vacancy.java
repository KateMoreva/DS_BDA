package java.ru.spbstu.search.entity.entry.enties.vacancy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import java.ru.spbstu.search.entity.entry.IEntityEntry;
import java.ru.spbstu.search.entity.entry.enties.profile.ProfField;
import java.ru.spbstu.search.entity.entry.enties.profile.Specialization;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Employment;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Experience;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Skill;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.address.Address;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.area.Area;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerInVacancy;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerSingle;

import static java.lang.String.format;

public class Vacancy implements IEntityEntry {
    public static final Vacancy NULL_VACANCY = new Vacancy();
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private URL url;
    @Setter
    private String description;
    @Setter
    private Schedule schedule = Schedule.NULL_SCHEDULE;
    @Setter
    @SerializedName("accept_handicapped")
    private Boolean acceptHandicapped;
    @Setter
    private Experience experience = Experience.NULL_EXPERIENCE;
    @Setter
    private Address address = Address.NULL_ADDRESS;
    @Getter
    @Setter
    @SerializedName("alternate_url")
    private String alternateUrl;
    @Setter
    private Employment employment = Employment.NULL_EMPLOYMENT;
    private Salary salary = Salary.NULL_SALARY;
    @Setter
    private Boolean archived;
    @Getter
    @Setter
    private Area area = Area.NULL_AREA;
    @Getter
    @Setter
    @SerializedName("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date createdAt;
    @Getter
    @Setter
    @SerializedName("published_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date publishedAt;
    @Getter
    @Setter
    private List<Object> relations = new ArrayList<>(0);
    @Getter
    @Setter
    private EmployerInVacancy employer = EmployerSingle.NULL_EMPLOYER;
    @Getter
    @Setter
    @SerializedName("response_letter_required")
    private Boolean responseLetterRequired;
    @Getter
    @Setter
    private VacancyType type = VacancyType.NULL_VACANCY_TYPE;
    @Setter
    private List<ProfField> profFields = new ArrayList<>(0);
    @Getter
    @Setter
    private List<Specialization> specializations;
    @Getter
    @Setter
    @SerializedName("key_skills")
    private List<Skill> keySkills;
    @Getter
    @Setter
    @SerializedName("professional_roles")
    private List<ProfField> professionalRoles;
    @Getter
    @Setter
    private List<Language> languages;

    public Salary getSalary() {
        if (salary == null) {
            setSalary(Salary.NULL_SALARY);
        }
        assert (salary != null);
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = (salary != null) ? salary : Salary.NULL_SALARY;
        assert (salary != null);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Boolean getAcceptHandicapped() {
        return acceptHandicapped;
    }

    public Experience getExperience() {
        return experience;
    }

    public Employment getEmployment() {
        return employment;
    }

    public Boolean getArchived() {
        return archived;
    }

    public List<ProfField> getProfFields() {
        return profFields;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return (address != null) ? address : Address.NULL_ADDRESS;
    }

    @Override
    public boolean isNull() {
        return getId() == null;
    }

    @Override
    public String toString() {
        return format("%s(%s)", getName(), getId());
    }
}
