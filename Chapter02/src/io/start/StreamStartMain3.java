package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain3 {
    public static void main(String[] args) throws IOException {
//        FileOutputStream fos = new FileOutputStream("temp/hello.dat",true);
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input  = {65,66,67};
        fos.write(input);

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] buffer = new byte[10];
        int readCount = fis.read(buffer,1,9);
        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(buffer));

        fos.close();
        fis.close();

    }
}
