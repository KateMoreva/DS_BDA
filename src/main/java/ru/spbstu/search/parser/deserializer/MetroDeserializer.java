package ru.spbstu.search.parser.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.search.entity.Entity;
import ru.spbstu.search.entity.IEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroCity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroCityEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroLine;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroLineEntity;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroStation;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MetroDeserializer implements JsonDeserializer<MetroCityEntity> {
    private static final Logger LOG = LoggerFactory.getLogger(MetroCityEntity.class);

    @Override
    public MetroCityEntity deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        try {
            return new MetroCityEntity(parseCities(element));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new JsonParseException(e);
        }
    }

    private List<MetroCity> parseCities(JsonElement element) throws MalformedURLException {
        List<MetroCity> result = new ArrayList<>();
        if (!element.isJsonNull()) {
            for (JsonElement cityElement : element.getAsJsonArray()) {
                JsonObject cityObj = (JsonObject) cityElement;
                String cityId = cityObj.get("id").getAsString();
                if (cityId != null) {
                    MetroCity newCity = new MetroCity();
                    newCity.setId(cityId);
                    newCity.setName(cityObj.get("name").getAsString());

                    JsonElement url = cityObj.get("url");
                    newCity.setUrl((!url.isJsonNull()) ? new URL(url.getAsString()) : null);

                    JsonElement lines = cityObj.get("lines");
                    newCity.setLines(parseLines(lines, newCity));
                    result.add((newCity));
                } else {
                    result.add(MetroCity.NULL_METRO_CITY);
                }
            }
        }
        return result;
    }

    private MetroLineEntity parseLines(JsonElement lines, MetroCity city) {
        List<MetroLine> result = new ArrayList<>();
        if (!lines.isJsonNull()) {
            for (JsonElement lineElement : lines.getAsJsonArray()) {
                JsonObject lineObj = (JsonObject) lineElement;
                String lineId = lineObj.get("id").getAsString();
                if (lineId != null) {
                    MetroLine newLine = new MetroLine();
                    newLine.setId(lineId);
                    newLine.setName(lineObj.get("name").getAsString());
                    newLine.setHexColor(lineObj.get("hex_color").getAsString());
                    newLine.setCity(city);
                    newLine.setStations(parseStations(lineObj.get("stations"), newLine));
                    result.add(newLine);
                } else {
                    result.add(MetroLine.NULL_METRO_LINE);
                }
            }
        }
        return new MetroLineEntity(result);
    }

    private IEntity<MetroStation> parseStations(JsonElement stationsElement, MetroLine line) {
        List<MetroStation> result = new ArrayList<>();
        if (!stationsElement.isJsonNull()) {
            for (JsonElement stationElement : stationsElement.getAsJsonArray()) {
                JsonObject stationObj = (JsonObject) stationElement;
                String id = stationObj.get("id").getAsString();
                if (id != null) {
                    MetroStation newStation = new MetroStation();
                    newStation.setId(id);
                    newStation.setName(stationObj.get("name").getAsString());
                    newStation.setLat(stationObj.get("lat").getAsDouble());
                    newStation.setLng(stationObj.get("lng").getAsDouble());
                    newStation.setLine(line);
                    newStation.setCity(line.getCity());
                    newStation.setOrder(stationObj.get("order").getAsInt());
                    result.add(newStation);
                } else {
                    result.add(MetroStation.NULL_METRO_STATION);
                }
            }
        }
        return new Entity<>(result, MetroStation.NULL_METRO_STATION);
    }
}
