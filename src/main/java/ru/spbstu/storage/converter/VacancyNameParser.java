package ru.spbstu.storage.converter;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class VacancyNameParser {
    private static final String LEVELS = "src/main/resources/levelKeyWords.json";
    private static final String SPECIALIZATION = "src/main/resources/specializationKeyWords.json";
    private static final String FIELDS = "src/main/resources/fieldKeyWords.json";
    private static final String SUBDOMAIN = "src/main/resources/subdomainKeyWords.json";
    private static final String LANGUAGE = "src/main/resources/languagesKeyWords.json";
    private static final String TECH = "src/main/resources/tech.json";

    private static final String DEFAULT_KEY = "DEFAULT";
    private static final String LEVEL_WITH_PLUS = "+";
    private final String vacancyName;

    private String detectedSpecialization;

    public VacancyNameParser(String vacancyName) {
        this.vacancyName = " " + vacancyName.toLowerCase();
        detectedSpecialization = "";
    }

    public String getSpecialization() {
        Map<String, List<String>> dictionary = getMap(SPECIALIZATION);
        detectedSpecialization = findForMap(vacancyName, dictionary);
        return detectedSpecialization;
    }

    public String getSpecialization(String text) {
        return findForMap(text, getMap(SPECIALIZATION));
    }

    public String getField() {
        Map<String, List<String>> dictionary = getMap(FIELDS);
        return findForMap(vacancyName, dictionary);
    }

    public String getField(String text) {
        return findForMap(text, getMap(FIELDS));
    }

    public String getSubDomain() {
        Map<String, List<String>> dictionary = getMap(SUBDOMAIN);
        return findForMap(vacancyName, dictionary);
    }

    public String getSubDomain(String text) {
        return findForMap(text, getMap(SUBDOMAIN));
    }

    public List<String> getLevel() {
        Map<String, List<String>> dictionary = getMap(LEVELS);
        List<String> levelList = findLevelListForMap(vacancyName, dictionary);
        if ((levelList.isEmpty() || levelList.get(0).isEmpty()) && hasDefaultLevel()) {
            return Collections.singletonList(dictionary.get(DEFAULT_KEY).get(0));
        }
        return levelList;
    }

    public String getLanguage() {
        Map<String, List<String>> dictionary = getMap(LANGUAGE);
        return findForMap(vacancyName, dictionary);
    }
    public String getLanguage(List<String> texts) {
        return findForMap(String.join(" ", texts).toLowerCase(), getMap(LANGUAGE));
    }

    public List<String> getLanguages(String text) {
        Map<String, List<String>> dictionary = getMap(LANGUAGE);
        return findListForMap(text, dictionary);
    }

    public List<String> getTech(String text) {
        Map<String, List<String>> dictionary = getMap(TECH);
        return findListForMap(text, dictionary);
    }

    private Map<String, List<String>> getMap(String file) {
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

    private List<String> findListForMap(String string, Map<String, List<String>> map) {
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> nameAndValue : map.entrySet()) {
            if (nameAndValue.getValue().stream().anyMatch(string::contains)) {
                res.add(nameAndValue.getKey());
            }
        }
        return res;
    }

    private List<String> findLevelListForMap(@NotNull String text,
                                             @NotNull Map<String, List<String>> map) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> nameAndValue : map.entrySet()) {
            List<String> valuesList = nameAndValue.getValue();
            for (String value : valuesList) {
                int valueIndexInText = text.indexOf(value);
                int valueWithPlusInText = text.indexOf(value + LEVEL_WITH_PLUS);
                if (valueIndexInText != -1 && valueIndexInText != valueWithPlusInText) {
                    result.add(nameAndValue.getKey());
                }
            }
        }
        return result;
    }

    private boolean hasDefaultLevel() {
        return !detectedSpecialization.isEmpty() &&
            (detectedSpecialization.contains("engineer")
                || detectedSpecialization.contains("QA"));
    }
}
