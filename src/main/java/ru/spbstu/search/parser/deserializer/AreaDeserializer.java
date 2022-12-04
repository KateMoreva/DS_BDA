package java.ru.spbstu.search.parser.deserializer;

import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.area.Area;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.area.AreaEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AreaDeserializer implements JsonDeserializer<AreaEntity> {

    @Override
    public AreaEntity deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        try {
            List<Area> areas = new ArrayList<>();
            for (JsonElement area : element.getAsJsonArray()) {
                areas.add(parseArea(area, null));
            }
            return new AreaEntity(areas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonParseException(e);
        }
    }

    private Area parseArea(JsonElement element, Area parentArea) {
        JsonObject object = (JsonObject) element;
        String id = object.get("id").getAsString();
        if (id == null || id.isEmpty()) {
            return Area.NULL_AREA;
        }
        Area newArea = new Area();
        newArea.setId(id);
        newArea.setName(object.get("name").getAsString());
        newArea.setParent(parentArea);

        JsonElement nestedAreas = object.get("areas");
        if (nestedAreas.isJsonArray()) {
            List<Area> nestedList = new ArrayList<>();
            for (JsonElement nested : nestedAreas.getAsJsonArray()) {
                nestedList.add(parseArea(nested, newArea));
            }
            newArea.setAreas(new AreaEntity(nestedList));
        }

        return newArea;
    }
}
