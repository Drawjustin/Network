package chatprogram.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static chatprogram.util.MyLogger.log;

public class Client {
    private static final int SERVER_PORT = 12345;
    private static final BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        Socket socket = new Socket();

        try {
            log(SERVER_PORT + " Server 연결 시도...");
            socket.connect(new InetSocketAddress("localhost", SERVER_PORT),
                    2000);
            log(SERVER_PORT + " Server 연결 성공!!!");
            try (DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream())){
                Thread outputThread = new Thread(()->{
                   while(true){
                       try {
                           String message = input.readUTF();
                           System.out.println(message);
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }
                   }
                });
                outputThread.start();
                while(true){
                    String message = sc.readLine();
                    output.writeUTF(message);

                    if(message.equals("/exit")){
                        break;
                    }

                }

            }catch (IOException e){
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            // 3. 소켓 안전하게 닫기
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error closing socket: " + e.getMessage());
                }
            }

        }

    }
}
