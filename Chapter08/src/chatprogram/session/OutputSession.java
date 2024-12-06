package chatprogram.session;

import java.io.*;
import java.net.Socket;

import static chatprogram.util.MyLogger.log;

public class OutputSession implements Runnable {
    private final DataOutputStream output;
    private final Socket socket;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public OutputSession(Socket socket) throws IOException {
        this.output = new DataOutputStream(socket.getOutputStream());
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            log(socket.getInetAddress()+ " OutputSession Start");
            while(true) {
                String message = br.readLine();
                output.writeUTF(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void close(){
        try {
            output.close();
            socket.close();
        } catch (IOException e) {
            log(socket.getLocalAddress()+ " <- 세션 자원 정리 실패");
        }
    }
}
