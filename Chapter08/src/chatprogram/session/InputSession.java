package chatprogram.session;

import java.io.*;
import java.net.Socket;

import static chatprogram.util.MyLogger.log;

public class InputSession implements Runnable {
    private final DataInputStream input;
    private final Socket socket;    
    public InputSession(Socket socket) throws IOException {
        this.input = new DataInputStream(socket.getInputStream());
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            log(socket.getInetAddress()+ " InputSession Start");
            while(true) {
                String message = input.readUTF();
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void close(){
        try {
            input.close();
            socket.close();
        } catch (IOException e) {
            log(socket.getLocalAddress()+ " <- 세션 자원 정리 실패");
        }
    }
}
