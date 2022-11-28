package ru.spbstu.images.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.spbstu.images.storage.FileInfo;
import ru.spbstu.images.storage.FileStorageService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/api/files/uploadFile")
    public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(uploadFileIntrenal(file));
    }

    @PostMapping("/api/files/uploadMultipleFiles")
    public ResponseEntity<List<FileInfo>> uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {
        List<FileInfo> fileInfos = Arrays.stream(files)
                .map(this::uploadFileIntrenal)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileInfos);
    }

    private FileInfo uploadFileIntrenal(@NotNull MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        return new FileInfo(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

}
