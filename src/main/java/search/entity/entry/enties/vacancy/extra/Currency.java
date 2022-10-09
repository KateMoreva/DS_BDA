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

    private Currency() {
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean def) {
        isDefault = def;
    }

}
