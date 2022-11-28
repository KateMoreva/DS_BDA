package ru.spbstu.images.storage;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.spbstu.images.exception.FileNotFoundException;
import ru.spbstu.images.exception.StorageException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(@NotNull FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileNotFoundException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @NotNull
    public String storeFile(@NotNull MultipartFile file) {
        try {
            validateFile(file);
            String fileName = getFileName(file);
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new StorageException("Couldn't store file " + getFileName(file) + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(@NotNull String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Couldn't read file: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("Couldn't read file: " + fileName, ex);
        }
    }

    private static void validateFile(@NotNull MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file: " + getFileName(file));
        }
        String fileName = getFileName(file);
        if (fileName.contains("..")) {
            throw new FileNotFoundException("Sorry! Filename contains invalid sequence: " + fileName);
        }
    }

    private static String getFileName(@NotNull MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        return StringUtils.cleanPath(originalFilename);
    }

}
