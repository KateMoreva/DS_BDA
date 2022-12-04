package java.ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Address {
    public static final Address NULL_ADDRESS = new Address();
    private static final String NULL_CITY = "NullCity";
    @Getter
    @Setter
    private String building;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String street;
    @Getter
    @Setter
    private String description;
    @Setter
    @SerializedName("metro")
    private Station station;
    @Getter
    @Setter
    private String raw;
    @Getter
    @Setter
    private Double lat;
    @Getter
    @Setter
    private Double lng;

    public Address() {
        setCity(NULL_CITY);
    }

    public Station getStation() {
        return station;
    }

    @Data
    public static final class Station {
        @SerializedName("station_id")
        private String stationId;
    }
}
