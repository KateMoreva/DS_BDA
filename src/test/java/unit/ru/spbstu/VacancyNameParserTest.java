package unit.ru.spbstu;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.base.CharMatcher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ru.spbstu.search.entity.entry.enties.profile.Specialization;
import ru.spbstu.storage.converter.VacancyNameParser;

class VacancyNameParserTest {
    private final String JJD = "Junior Java разработчик";
    private final String I_OR_J_JD = "Intern/Junior Java Developer";
    private final String JPLUS_JD = "Junior+ Java Developer";
    private final String SCppD = "Senior C++/Qt developer";
    private final String M1C = "Разработчик 1C";
    private final String QA = "Инженер по тестированию API/Backend (команда Единого видео)";
    private final String MML = "Machine Learning Engineer в команду NLP разработки (Python)";
    private final String MULTI = "Senior UX/UI Product Designer (Web, iOS, Android) ИТ-компания, удаленно";
    private final String WHO_KNOWS = "Ведущий специалист разработки проектов на ПЛИС";
    private final String WHO_KNOWS2 = "Главный специалист разработчик программируемых логических интегральных схем";


    @Test
    void parse_juniorJavaDev() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(JJD);
        List<String> level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        Assertions.assertEquals(Collections.singletonList("Junior"), level);
        Assertions.assertEquals("Java", language);
        Assertions.assertEquals("Software engineer", spec);
    }

    @Test
    void parse_seniorCplusplusDev() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(SCppD);
        List<String> level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        Assertions.assertEquals(Collections.singletonList("Senior"), level);
        Assertions.assertEquals("C++", language);
        Assertions.assertEquals("Software engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }

    @Test
    void parse_middle1C() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(M1C);
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        List<String> level = vacancyNameParser.getLevel();
        Assertions.assertEquals(Collections.singletonList("Middle"), level);
        Assertions.assertEquals("1C", language);
        Assertions.assertEquals("Software engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }

    @Test
    void parse_MML() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(MML);
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        List<String> level = vacancyNameParser.getLevel();
        Assertions.assertEquals(Collections.singletonList("Middle"), level);
        Assertions.assertEquals("Python", language);
        Assertions.assertEquals("ML engineer", spec);
        Assertions.assertEquals("AI", field);
        Assertions.assertEquals("NLP", sub);
    }

    @Test
    void parse_whoKnows() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(WHO_KNOWS);
        List<String> level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        Assertions.assertEquals(Collections.singletonList("Lead"), level);
        Assertions.assertEquals("", language);
        Assertions.assertEquals("Embedded-system engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }

    @Test
    void parse_moreThanOneLevel() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(I_OR_J_JD);
        List<String> level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        Assertions.assertEquals(Arrays.asList("Intern", "Junior"), level);
        Assertions.assertEquals("Java", language);
        Assertions.assertEquals("Software engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }

    @Test
    void parse_LevelWithPlus() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(JPLUS_JD);
        List<String> level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        Assertions.assertEquals(Collections.singletonList("Junior+"), level);
        Assertions.assertEquals("Java", language);
        Assertions.assertEquals("Software engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }

    @Test
    public void dll() {
        List<String> text = new ArrayList<>();
        text.add("английский язык");
        text.add("Java SE");
        text.add(":ооп");
        text.forEach(el -> System.out.println(CharMatcher.ascii().negate().trimFrom(el)));
        List<String> res = text.stream()
            .filter(elem -> !CharMatcher.ascii().negate().trimFrom(elem).isBlank() && !getList("src/main/resources/techKeyWords.json").contains(elem.toLowerCase()))
            .collect(Collectors.toList());
        System.out.println(res);
    }


    @Test
    public void area() {
        List<Specialization> specializations = getSpec();
      List<Specialization> it = specializations.stream().filter(spec -> Pattern.matches("1[\".][0-9]{1,3}", spec.getId())).collect(Collectors.toList());
        Assertions.assertEquals(37, it.size());
    }

    private List<String> getList(String file) {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get(file)));
            Type lisType = new TypeToken<List<String>>() {
            }.getType();
            return gson.fromJson(json, lisType);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<Specialization> getSpec() {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/api.json")));
            Type lisType = new TypeToken<List<Specialization>>() {
            }.getType();
            return gson.fromJson(json, lisType);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
