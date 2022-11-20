package unit.ru.spbstu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.spbstu.storage.converter.VacancyNameParser;

class VacancyNameParserTest {
    private final String JJD = "Junior Java разработчик";
    private final String SCppD = "Senior C++/Qt developer";
    private final String WHO_KNOWS = "Ведущий специалист разработки проектов на ПЛИС";


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
