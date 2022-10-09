package search.entity.entry.enties.vacancy.extra;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import search.entity.entry.enties.vacancy.ResumeContactsSiteType;
import search.entity.entry.enties.vacancy.VacancyType;
import search.entity.entry.enties.vacancy.extra.employer.EmployerType;
import search.fields.VacancySearchFields;

import java.util.List;

@Data
public class ExtraInfoEntityContainer {
    @SerializedName("resume_contacts_site_type")
    private List<ResumeContactsSiteType> resumeContactsSiteType;
    private List<Schedule> schedule;
    @SerializedName("business_trip_readiness")
    private List<BusinessTripReadiness> businessTripReadiness;
    @SerializedName("vacancy_search_fields")
    private List<VacancySearchFields> vacancySearchFields;
    private List<Currency> currency;
    @SerializedName("travel_time")
    private List<TravelTime> travelTime;
    @SerializedName("education_level")
    private List<EducationLevel> educationLevel;
    @SerializedName("relocation_type")
    private List<RelocationType> relocationType;
    @SerializedName("resume_access_type")
    private List<ResumeAccessType> resumeAccessType;
    private List<Employment> employment;
    @SerializedName("vacancy_label")
    private List<Label> label;
    private List<Gender> gender;
    @SerializedName("language_level")
    private List<LanguageLevel> languageLevel;
    private List<Experience> experience;
    @SerializedName("preferred_contact_type")
    private List<PreferredContactType> preferredContactType;
    @SerializedName("vacancy_search_order")
    private List<Order> order;
    @SerializedName("vacancy_type")
    private List<VacancyType> vacancyType;
    @SerializedName("site_lang")
    private List<SiteLang> siteLang;
    @SerializedName("employer_type")
    private List<EmployerType> employerType;
}
