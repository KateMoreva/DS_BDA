package java.ru.spbstu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.ru.spbstu.loader.ContentLoaderFactory;
import java.ru.spbstu.loader.IContentLoader;

@Configuration
public class SpringBeansConfig {

    @Bean
    public IContentLoader contentLoader() {
        return ContentLoaderFactory.newInstanceLongTermCache();
    }

}
