package ru.spbstu.search.parser.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateDeserializer implements JsonDeserializer<Date> {

    public static Date parse(String iso8601date) throws java.text.ParseException {
        String date = iso8601date;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        if (date.endsWith("Z")) {
            date = date.substring(0, date.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;
            String s0 = date.substring(0, date.length() - inset);
            String s1 = date.substring(date.length() - inset, date.length());
            date = s0 + "GMT" + s1;
        }
        return df.parse(date);
    }

    @Override
    public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
        try {
            String dateStr = element.getAsString();
            return parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            throw new JsonParseException(e);
        }
    }
}
