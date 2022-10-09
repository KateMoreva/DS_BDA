package search.entity.entry.enties.vacancy.extra.address;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class MetroStation extends MetroLine {
    public static final MetroStation NULL_METRO_STATION = new MetroStation();

    private static final Map<String, MetroStation> STATIONS = new HashMap<>();

    static {
        for (MetroCity city : MetroInitializer.getInstance().getMetroCities()) {
            for (MetroLine line : city.getLines()) {
                for (MetroStation station : line.getStations()) {
                    STATIONS.put(station.getId(), station);
                }
            }
        }
    }

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

    public static MetroStation getStationById(String stationId) {
        return STATIONS.get(stationId);
    }
}
