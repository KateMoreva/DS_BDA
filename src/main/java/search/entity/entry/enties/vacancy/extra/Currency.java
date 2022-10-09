package search.entity.entry.enties.vacancy.extra;

import com.google.gson.annotations.SerializedName;
import loader.ContentLoaderFactory;
import loader.IContentLoader;
import loader.UrlConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import search.SearchException;
import search.entity.Entity;
import search.entity.IEntity;
import search.entity.entry.AbstractEntityEntry;

import java.util.Collections;

@ToString(of = {"id", "abbr"})
public final class Currency extends AbstractEntityEntry {
    public static final Currency NULL_CURRENCY = new Currency();
    public static IEntity<Currency>
            CURRENCIES;

    public static final Currency
            RUR = CURRENCIES.getById("RUR");

    @Getter
    @Setter
    @SerializedName("code")
    private String id;
    @Getter
    @Setter

    private Double rate;
    @Getter
    @Setter

    private String abbr;
    @SerializedName("default")
    private Boolean isDefault;
    private IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();

    private Currency() {
        try {
            String content = loader.loadContent(UrlConstants.DICTINARIES_URL);
            ExtraInfoParser parse = new ExtraInfoParser();
            ExtraInfoEntityContainer container = parse.parse(content);
            CURRENCIES = new Entity<>(container.getCurrency(), NULL_CURRENCY);
        } catch (SearchException e) {
            e.printStackTrace();
            CURRENCIES = new Entity<>(Collections.emptyList(), NULL_CURRENCY);
        }
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean def) {
        isDefault = def;
    }

}
