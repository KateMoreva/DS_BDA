package ru.spbstu.images.storage;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FileInfo {

    private String fileName;
    private String fileDownloadUrl;
    private String fileType;
    private long size;

}
