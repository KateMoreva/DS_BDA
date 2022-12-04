package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import lombok.Getter;
import lombok.Setter;

public class MetroStation extends MetroLine {
    public static final MetroStation NULL_METRO_STATION = new MetroStation();

    @Getter
    @Setter
    private MetroLine line;
    @Getter
    @Setter
    private Integer order;
    @Getter
    @Setter
    private Double lat;
    @Getter
    @Setter
    private Double lng;

}
