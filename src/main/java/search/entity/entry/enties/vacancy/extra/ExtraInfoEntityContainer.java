package search.entity.entry.enties.vacancy.extra;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import search.entity.entry.enties.vacancy.VacancyType;
import search.entity.entry.enties.vacancy.extra.employer.EmployerType;
import search.fields.VacancySearchFields;

import java.util.List;

@Data
public class ExtraInfoEntityContainer {
    private List<Schedule> schedule;
    @SerializedName("vacancy_search_fields")
    private List<VacancySearchFields> vacancySearchFields;
    private List<Currency> currency;
    @SerializedName("travel_time")
    private List<TravelTime> travelTime;
    @SerializedName("education_level")
    private List<EducationLevel> educationLevel;
    private List<Employment> employment;
    @SerializedName("vacancy_label")
    private List<Label> label;
    private List<Gender> gender;
    @SerializedName("language_level")
    private List<LanguageLevel> languageLevel;
    private List<Experience> experience;
    @SerializedName("vacancy_search_order")
    private List<Order> order;
    @SerializedName("vacancy_type")
    private List<VacancyType> vacancyType;
    @SerializedName("employer_type")
    private List<EmployerType> employerType;
}
