package ru.spbstu.images.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@Setter
@Getter
public class FileStorageProperties {

    private String uploadDir;

}
