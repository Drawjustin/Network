package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {
    public static void main(String[] args) {
        File file = new File("temp/..");
        System.out.println("path = " + file.getPath());
        // 절대 경로
        System.out.println("Absolute path = " + file.getAbsolutePath());
        // 정규 경로
        try {
            System.out.println("Canonical path = " + file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }


    }
}
