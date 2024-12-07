package chatprogram.session;

import java.io.*;

public class DataStreamHandler implements StreamHandler {

    private static final StreamHandler streamHandler = new DataStreamHandler();


    private DataStreamHandler() {
    }

    public static StreamHandler getStreamHandler() {
        return streamHandler;
    }

    @Override
    public void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        // 예제 데이터 처리

        DataInputStream dataInputStream = (DataInputStream) inputStream;
        DataOutputStream dataOutputStream = (DataOutputStream) outputStream;

        String message = dataInputStream.readUTF();
        dataOutputStream.writeUTF("Echo: " + message);
    }
}

