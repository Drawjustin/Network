package io.streams;

import java.io.*;

public class PrintStreamEtcMain {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt");
        PrintStream printStream = new PrintStream(fos);
        printStream.println("hello");
        printStream.println(true);
        printStream.printf("heelo %s", "world");
    }
}
