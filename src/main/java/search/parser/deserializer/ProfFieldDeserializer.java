package search.parser.deserializer;

import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import search.entity.Entity;
import search.entity.entry.enties.profile.ProfField;
import search.entity.entry.enties.profile.ProfFieldEntity;
import search.entity.entry.enties.profile.Specialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProfFieldDeserializer implements JsonDeserializer<ProfFieldEntity> {

    @Override
    public ProfFieldEntity deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        try {
            List<ProfField> fields = new ArrayList<>();
            for (JsonElement field : element.getAsJsonArray()) {
                JsonObject object = (JsonObject) field;
                fields.add(parseProfField(object));
            }
            return new ProfFieldEntity(fields);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonParseException(e);
        }
    }

    private ProfField parseProfField(JsonObject object) {
        ProfField newField;
        String id = object.get("id").getAsString();
        if (id != null && !id.isEmpty()) {
            newField = new ProfField();
            newField.setId(id);
            newField.setName(object.get("name").getAsString());
            JsonElement specializations = object.get("specializations");
            if (specializations.isJsonArray()) {
                List<Specialization> newSpecializations = new ArrayList<>();
                for (JsonElement specializationElement : specializations.getAsJsonArray()) {
                    JsonObject specialization = (JsonObject) specializationElement;
                    newSpecializations.add(parseSpecialization(specialization));
                }
                newField.setSpecializations(new Entity<>(newSpecializations, Specialization.NULL_SPECIALIZATION));
            }
        } else {
            newField = ProfField.NULL_PROF_FIELD;
        }
        return newField;
    }

    private Specialization parseSpecialization(JsonObject specialization) {
        Specialization newSpecialization;
        String specId = specialization.get("id").getAsString();
        if (specId != null && !specId.isEmpty()) {
            newSpecialization = new Specialization();
            newSpecialization.setId(specId);
            newSpecialization.setName(specialization.get("name").getAsString());
        } else {
            newSpecialization = Specialization.NULL_SPECIALIZATION;
        }
        return newSpecialization;
    }
}