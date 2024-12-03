package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();
        byte[] buffer = new byte[BUFFER_SIZE];

        byte[] bytes = fis.readAllBytes();

        fis.close();
        long endTime = System.currentTimeMillis();
        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + bytes.length / 1024 / 1024 + "MB");
        System.out.println("Time token : " + (endTime-startTime) + "ms");
    }
}
