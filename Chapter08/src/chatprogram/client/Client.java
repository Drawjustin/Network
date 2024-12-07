package chatprogram.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static chatprogram.util.MyLogger.log;

public class Client {
    private static final int SERVER_PORT = 12345;
    private static final BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Socket socket = new Socket();

        try {
            log(SERVER_PORT + " Server 연결 시도...");
            socket.connect(new InetSocketAddress("localhost", SERVER_PORT), 2000);
            log(SERVER_PORT + " Server 연결 성공!!!");

            try (DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                // 입력 스레드
                Thread inputThread = new Thread(() -> {
                    try {
                        while (!socket.isClosed()) {
                            String message = input.readUTF();
                            System.out.println("서버로부터 수신: " + message);
                        }
                    } catch (IOException e) {
                        if (!socket.isClosed()) {
                            System.err.println("입력 스레드 오류: " + e.getMessage());
                        }
                    } finally {
                        log("입력 스레드 종료");
                    }
                });
                inputThread.start();

                // 출력 루프
                while (!socket.isClosed()) {
                    log("출력할 메세지 입력 : ");
                    String message = sc.readLine();
                    output.writeUTF(message);

                    if (message.equals("/exit")) {
                        log("서버 연결 종료 요청");
                        socket.close(); // 클라이언트 소켓 닫기
                        break;
                    }
                }

            } catch (IOException e) {
                System.err.println("클라이언트 통신 오류: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("서버 연결 실패: " + e.getMessage());
        } finally {
            // 소켓 안전하게 닫기
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                    log("소켓 정상 종료");
                } catch (IOException e) {
                    System.err.println("소켓 닫기 실패: " + e.getMessage());
                }
            }
        }
    }
}
