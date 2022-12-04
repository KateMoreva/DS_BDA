package ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.loader.IContentLoader;
import ru.spbstu.loader.UrlConstants;
import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.parser.MetroParser;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MetroStationProvider {

    private final IContentLoader contentLoader;
    private final MetroParser parser;
    private final Map<String, MetroStation> stationMap = new HashMap<>();

    @Autowired
    public MetroStationProvider(IContentLoader contentLoader,
                                MetroParser parser) {
        this.contentLoader = contentLoader;
        this.parser = parser;
    }

    @PostConstruct
    public void init() {
        MetroCityEntity metroCities = loadMetro();
        transformAndSet(metroCities);
    }

    public MetroStation getStationById(String stationId) {
        return stationMap.get(stationId);
    }

    @NotNull
    private MetroCityEntity loadMetro() {
        try {
            log.debug("Load metro");
            String content = contentLoader.loadContent(UrlConstants.METRO_URL, Collections.emptyMap());
            return parser.parse(content);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new MetroCityEntity();
        }
    }

    private void transformAndSet(@NotNull MetroCityEntity metroCities) {
        for (MetroCity city : metroCities) {
            for (MetroLine line : city.getLines()) {
                for (MetroStation station : line.getStations()) {
                    stationMap.put(station.getId(), station);
                }
            }
        }
    }

}
