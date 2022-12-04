package ru.spbstu;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    public static void zipData(List<String> sourceFiles,
                               Set<String> innerArchiveFiles,
                               Set<String> excludeFiles,
                               String resultFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(resultFile)) {
            try (ZipOutputStream zipOut = new ZipOutputStream(fos)) {
                for (String sourceFile : sourceFiles) {
                    File file = new File(sourceFile);
                    zipFile(file, file.getName(), innerArchiveFiles, excludeFiles, zipOut);
                }
            }
            fos.flush();
        }
    }

    private static File preprocess(String sourceFile,
                                   Set<String> innerArchiveFiles,
                                   Set<String> excludeFiles) throws IOException {
        if (excludeFiles.contains(sourceFile)) {
            System.out.println("File: " + sourceFile + " in exclude files, skipping...");
            return null;
        }
        if (innerArchiveFiles.contains(sourceFile)) {
            Set<String> newInnerArchiveFiles = new HashSet<>(innerArchiveFiles);
            newInnerArchiveFiles.remove(sourceFile);
            zipData(Collections.singletonList(sourceFile), newInnerArchiveFiles, excludeFiles, sourceFile + ".zip");
            return null;
        } else {
            return new File(sourceFile);
        }
    }

    private static void zipFile(File fileToZip,
                                String fileName,
                                Set<String> innerArchiveFiles,
                                Set<String> excludeFiles,
                                ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        File file = preprocess(fileName, innerArchiveFiles, excludeFiles);
        if (file == null) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), innerArchiveFiles, excludeFiles, zipOut);
            }
            return;
        }
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

}
