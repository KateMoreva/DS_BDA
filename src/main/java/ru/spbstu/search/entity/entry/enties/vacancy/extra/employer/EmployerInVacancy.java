package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.employer;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

public class EmployerInVacancy extends Employer {
    @Getter
    @Setter
    private URL url;
    @Getter
    @Setter
    @SerializedName("alternate_url")
    private URL alternateUrl;
    @Getter
    @Setter
    @SerializedName("logo_urls")
    private LogoUrls logoUrls;
    @Getter
    @Setter
    private Boolean trusted;

    public EmployerInVacancy() {
        super(null);
    }
}
