package unit.ru.spbstu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.spbstu.storage.converter.VacancyNameParser;

class VacancyNameParserTest {
    private final String JJD = "Junior Java разработчик";
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
        String level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        Assertions.assertEquals("Junior", level);
        Assertions.assertEquals("Java", language);
        Assertions.assertEquals("Software engineer", spec);
    }

    @Test
    void parse_seniorCplusplusDev() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(SCppD);
        String level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        Assertions.assertEquals("Senior", level);
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
        String level = vacancyNameParser.getLevel();
        Assertions.assertEquals("Middle", level);
        Assertions.assertEquals("1C", language);
        Assertions.assertEquals("Software engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }

    @Test
    void parse_MML(){
        VacancyNameParser vacancyNameParser = new VacancyNameParser(MML);
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        String level = vacancyNameParser.getLevel();
        Assertions.assertEquals("Middle", level);
        Assertions.assertEquals("Python", language);
        Assertions.assertEquals("ML engineer", spec);
        Assertions.assertEquals("AI", field);
        Assertions.assertEquals("NLP", sub);
    }

    @Test
    void parse_whoKnows() {
        VacancyNameParser vacancyNameParser = new VacancyNameParser(WHO_KNOWS);
        String level = vacancyNameParser.getLevel();
        String language = vacancyNameParser.getLanguage();
        String spec = vacancyNameParser.getSpecialization();
        String field = vacancyNameParser.getField();
        String sub = vacancyNameParser.getSubDomain();
        Assertions.assertEquals("Lead", level);
        Assertions.assertEquals("", language);
        Assertions.assertEquals("Embedded-system engineer", spec);
        Assertions.assertEquals("", field);
        Assertions.assertEquals("", sub);
    }


}
