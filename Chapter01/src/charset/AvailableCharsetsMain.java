package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

public class AvailableCharsetsMain {

    public static void main(String[] args) {
        // 이용 가능한 모든 Charset 자바 + OS
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }
        System.out.println("=====");

        // 문자로 조회(대소문자 구분X), MS949, ms949, x-windows-949
        Charset ms949 = Charset.forName("MS949");;
        System.out.println("ms949 = " + ms949);

        // 별칭 조회
        for (String alias : ms949.aliases()) {
            System.out.println("alias = " + alias);
        }

        // UTF-8 문자로 조회
        Charset charset1 = Charset.forName("UTF-8");
        System.out.println("charset1 = " + charset1);

        // UTF-8 상수로 조회

        Charset utf8 = StandardCharsets.UTF_8;
        System.out.println("utf8 = " + utf8);

        // 시스템의 기본 Charset 조회
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);




    }
}
