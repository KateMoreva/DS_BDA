package search.entity.entry.enties.vacancy.extra.address;

import search.entity.entry.AbstractEntityEntry;

import java.net.URL;

public class MetroCity extends AbstractEntityEntry {
    public static final MetroCity NULL_METRO_CITY = new MetroCity();
    public static final MetroCityEntity CITIES = MetroInitializer.getInstance().getMetroCities();
    private URL url;
    private MetroLineEntity lines = new MetroLineEntity();

    public MetroLineEntity getLines() {

        return lines;
    }

    public void setLines(MetroLineEntity lines) {
        this.lines = lines;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
