package unit.ru.spbstu;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import java.ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;


class VacancyParserTest {
    private final String FILE = "src/test/resources/vacancy1.json";

    @Test
    void parseVacancy_Success() throws Exception {
        Gson gson = new Gson();
        String json = readFileAsString(FILE);
        Vacancy vacancy = gson.fromJson(json, Vacancy.class);
        JSONObject jsonObject = new JSONObject(json);
        Assertions.assertEquals(jsonObject.get("id"), vacancy.getId());
        Assertions.assertEquals(jsonObject.get("name"), vacancy.getName());
    }

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
