package ru.spbstu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DistributiveUtil {

    private static final String PATH_TO_GRADLE_PROJECT = "." + File.separator;
    private static final String GRADLEW_EXECUTABLE = "gradlew";
    private static final String BLANK = " ";
    private static final String GRADLE_TASK = "bootJar";

    private static final String BOOT_JAR_FILE = File.separator + "build" + File.separator + "libs" + File.separator + "DS_BDA-1.0.jar";
    private static final String BASE_SCRIPTS_DIR = File.separator + "scripts";
    private static final String DOCKER_BASE_DIR = File.separator + "docker";
    private static final String DOCKER_ES_DATA_DIR = File.separator + "docker" + File.separator + "esdata";
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pls provide project directory as parameter!");
        }
        createDistributive(args[0]);
    }

    private static void createDistributive(String baseProjectPath) throws IOException {
        List<String> filesToArchive = new ArrayList<>();
        File fatJarFile = new File(baseProjectPath + BOOT_JAR_FILE);
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
                Collections.singleton(DOCKER_ES_DATA_DIR),
                Collections.emptySet(),
                "result.zip"
        );
        new File(baseProjectPath + "/" + DOCKER_ES_DATA_DIR + ".zip").delete();
    }

    private static void tryToCreateFatJar(String baseProjectPath) {
        try {
            Runtime.getRuntime().exec(PATH_TO_GRADLE_PROJECT + GRADLEW_EXECUTABLE + BLANK + GRADLE_TASK);
            long start = System.currentTimeMillis();
            File file = new File(baseProjectPath + BOOT_JAR_FILE);
            while (!file.exists() && start - System.currentTimeMillis() < 1000 * 30) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create fat jar from code.");
        }
    }

}
