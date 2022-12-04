package java.ru.spbstu.search.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.ru.spbstu.search.entity.entry.enties.profile.ProfFieldEntity;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.ConstantsProvider;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroCityEntity;
import java.ru.spbstu.search.entity.entry.enties.vacancy.extra.area.AreaEntity;
import java.ru.spbstu.search.parser.deserializer.AreaDeserializer;
import java.ru.spbstu.search.parser.deserializer.DateDeserializer;
import java.ru.spbstu.search.parser.deserializer.MetroDeserializer;
import java.ru.spbstu.search.parser.deserializer.ProfFieldDeserializer;
import java.ru.spbstu.search.parser.deserializer.SalaryDeserializer;

import java.util.Date;

@Configuration
public class GsonConfiguration {

    private final ConstantsProvider constantsProvider;

    @Autowired
    public GsonConfiguration(ConstantsProvider constantsProvider) {
        this.constantsProvider = constantsProvider;
    }

    @Bean
    public Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Salary.class, new SalaryDeserializer(constantsProvider))
                .registerTypeAdapter(MetroCityEntity.class, new MetroDeserializer())
                .registerTypeAdapter(AreaEntity.class, new AreaDeserializer())
                .registerTypeAdapter(ProfFieldEntity.class, new ProfFieldDeserializer());
        return builder.create();
    }


}
