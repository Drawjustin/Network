package was.v1;

import was.v2.CustomHttpServerV2;

import java.io.IOException;

public class ServerMain {

    private static final int PORT = 12345;


    public static void main(String[] args) throws IOException {
//        HttpServerV1 server = new HttpServerV1(PORT);
        CustomHttpServerV2 server = new CustomHttpServerV2(PORT);
        server.start();
    }
}
