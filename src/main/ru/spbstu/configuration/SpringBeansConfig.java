package ru.spbstu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.spbstu.loader.ContentLoaderFactory;
import ru.spbstu.loader.IContentLoader;

@Configuration
public class SpringBeansConfig {

    @Bean
    public IContentLoader contentLoader() {
        return ContentLoaderFactory.newInstanceLongTermCache();
    }

}
