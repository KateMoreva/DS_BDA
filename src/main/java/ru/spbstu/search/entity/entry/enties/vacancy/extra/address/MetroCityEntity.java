package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import java.ru.spbstu.search.entity.Entity;

import java.util.List;

public class MetroCityEntity extends Entity<MetroCity> {
    public MetroCityEntity() {
        super(MetroCity.NULL_METRO_CITY);
    }

    public MetroCityEntity(List<MetroCity> metroCities) {
        super(MetroCity.NULL_METRO_CITY);
        for (MetroCity metroCity : metroCities) {
            addEntry(metroCity);
        }
    }

}
