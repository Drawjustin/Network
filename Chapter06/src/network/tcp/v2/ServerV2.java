package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV2 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");

        ServerSocket serverSocket = new ServerSocket(PORT);

        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        Socket socket = serverSocket.accept();
        log("소켓 연결 : " + socket);

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


    }
}