package java.ru.spbstu.search.entity.entry.enties.vacancy;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString//(exclude = "items")
public class VacancyPage {

    private Integer found;

    private Integer pages;

    @SerializedName("per_page")
    private Integer perPage;

    private Integer page;

    private List<Vacancy> items;
}
