package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain4 {
    public static void main(String[] args) throws IOException {
//        FileOutputStream fos = new FileOutputStream("temp/hello.dat",true);
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input  = {65,66,67,(byte) '\n'};
        fos.write(input);

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] readBytes = fis.readAllBytes();
        System.out.println(Arrays.toString(readBytes));

        fos.close();
        fis.close();

    }
}
