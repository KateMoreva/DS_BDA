package ru.spbstu.storage.converter;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VacancyNameParser {
    private static final String LEVELS = "src/main/resources/levelKeyWords.json";
    private static final String SPECIALIZATION = "src/main/resources/specializationKeyWords.json";
    private static final String FIELDS = "src/main/resources/fieldKeyWords.json";
    private static final String SUBDOMAIN = "src/main/resources/subdomainKeyWords.json";
    private static final String LANGUAGE = "src/main/resources/languagesKeyWords.json";
    private final String vacancyName;

    public VacancyNameParser(String vacancyName) {
        this.vacancyName = vacancyName.toLowerCase();
    }

    public String getSpecialization() {
        Map<String, List<String>> dictionary = getMap(SPECIALIZATION);
        return findForMap(vacancyName, dictionary);
    }

    public String getField() {
        Map<String, List<String>> dictionary = getMap(FIELDS);
        return findForMap(vacancyName, dictionary);
    }

    public String getSubDomain() {
        Map<String, List<String>> dictionary = getMap(SUBDOMAIN);
        return findForMap(vacancyName, dictionary);
    }

    public String getLevel() {
        Map<String, List<String>> dictionary = getMap(LEVELS);
        return findForMap(vacancyName, dictionary);
    }

    public String getLanguage() {
        Map<String, List<String>> dictionary = getMap(LANGUAGE);
        return findForMap(vacancyName, dictionary);
    }

    private Map<String, List<String>> getMap(String file)
    {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get(file)));
            Type mapType = new TypeToken<Map<String, List<String>>>() {
            }.getType();
            return gson.fromJson(json, mapType);
        } catch (Exception e) {
            log.error("Could not parse [{}]", file);
            return Collections.emptyMap();
        }
    }
    private String findForMap(String string, Map<String, List<String>> map) {
        for (Map.Entry<String, List<String>> nameAndValue : map.entrySet()) {
            if (nameAndValue.getValue().stream().anyMatch(string::contains)) {
                return nameAndValue.getKey();
            }
        }
        return "";
    }
}