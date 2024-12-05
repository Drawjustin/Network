package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV3 implements Runnable{
    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while(true){
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();
                log("client -> server : " + received);

                if(received.equals("exit")){
                    break;
                }
                // 클라이언트에게 문자 보내기
                String toSend = "World!";
                output.writeUTF(received+ " " + toSend);
                log("client <- server : " + received + toSend);

            }
            // 자원 정리
            log("자원 정리 : " + socket);
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
