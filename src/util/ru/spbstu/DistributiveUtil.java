package ru.spbstu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DistributiveUtil {

    private static String PATH_TO_GRADLE_PROJECT = "./";
    private static String GRADLEW_EXECUTABLE = "gradlew";
    private static String BLANK = " ";
    private static String GRADLE_TASK = "customFatJar";

    private static final String FAT_JAR_FILE = "/build/libs/DS_DBA-1.0-fat.jar";
    private static final String BASE_SCRIPTS_DIR = "/scripts";
    private static final String DOCKER_BASE_DIR = "/docker";
    private static final String DOCKER_ES_DATA_DIR = "/docker/esdata";
    public static void main(String[] args) throws IOException {
        createDistributive("/Users/vladislav.zybkin/Documents/ucheba/masterdegree/bigdata/DS_BDA");
    }

    private static void createDistributive(String baseProjectPath) throws IOException {
        List<String> filesToArchive = new ArrayList<>();
        File fatJarFile = new File(baseProjectPath + FAT_JAR_FILE);
        if (!fatJarFile.exists()) {
            tryToCreateFatJar(baseProjectPath);
        }
        filesToArchive.add(fatJarFile.getAbsolutePath());
        File[] scriptFiles = new File(baseProjectPath + BASE_SCRIPTS_DIR).listFiles();
        if (scriptFiles != null) {
            Arrays.stream(scriptFiles)
                    .map(File::getAbsolutePath)
                    .forEach(filesToArchive::add);
        } else {
            System.out.println("No script were found. Is it normal? Continue to create distributive.");
        }
        filesToArchive.add(baseProjectPath + DOCKER_BASE_DIR);
        FileUtil.zipData(
                filesToArchive,
                Collections.singleton("docker/esdata"),
                Collections.emptySet(),
                "result.zip"
        );
    }

    private static void tryToCreateFatJar(String baseProjectPath) {
        try {
            Runtime.getRuntime().exec(PATH_TO_GRADLE_PROJECT + GRADLEW_EXECUTABLE + BLANK + GRADLE_TASK);
            long start = System.currentTimeMillis();
            File file = new File(baseProjectPath + FAT_JAR_FILE);
            while (!file.exists() && start - System.currentTimeMillis() < 1000 * 30) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create fat jar from code.");
        }
    }

}
