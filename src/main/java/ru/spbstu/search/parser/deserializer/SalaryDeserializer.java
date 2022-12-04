package java.ru.spbstu.search.parser.deserializer;

import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.ConstantsProvider;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;

import java.lang.reflect.Type;

@Slf4j
@Component
public class SalaryDeserializer implements JsonDeserializer<Salary> {

    private final ConstantsProvider constants;

    public SalaryDeserializer(ConstantsProvider constants) {
        this.constants = constants;
    }

    @Override
    public Salary deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        try {
            JsonObject object = (JsonObject) element;

            JsonElement toElement = object.get("to");
            Integer to = (toElement.isJsonPrimitive()) ? toElement.getAsInt() : null;

            JsonElement fromElement = object.get("from");
            Integer from = (fromElement.isJsonPrimitive()) ? fromElement.getAsInt() : null;

            JsonElement currencyElement = object.get("currency");
            String currency = (currencyElement.isJsonPrimitive()) ? currencyElement.getAsString() : null;
//            if (currencyElement.isJsonPrimitive()) {
//                String currencyId = currencyElement.getAsString();
//                currency = constants.getCurrency().CURRENCIES.getById(currencyId);
//            }

            return new Salary(from, to, currency);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JsonParseException(e);
        }
    }
}
