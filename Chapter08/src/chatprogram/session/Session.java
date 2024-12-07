package chatprogram.session;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

import static chatprogram.util.MyLogger.log;

public class Session implements Runnable {
    private final Socket socket;
    private final SessionManager sessionManager;
    private final StreamHandler streamHandler;

    public Session(Socket socket, SessionManager sessionManager, StreamHandler streamHandler) {
        this.socket = socket;
        this.sessionManager = sessionManager;
        this.streamHandler = streamHandler;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            while (!socket.isClosed()) {
                String message = dataInputStream.readUTF();
                StringTokenizer stk = new StringTokenizer(message, "|");
                String order = stk.nextToken();

                if (order.equals("/join")) {
                    if (stk.hasMoreTokens()) {
                        String nickName = stk.nextToken();
                        log(nickName + " 채팅방 접속 성공!");
                        new Thread(new DataInputTask(socket)).start();
                        new Thread(new DataOutputTask(socket)).start();
                    }
                } else if (order.equals("/exit")) {
                    log("클라이언트 종료 요청 수신");
                    break;
                } else {
                    log("알 수 없는 명령: " + order);
                }
            }

        } catch (IOException e) {
            System.err.println("Session communication failed: " + e.getMessage());
        } finally {
            try {
                socket.close();
                log("소켓이 정상적으로 닫혔습니다.");
            } catch (IOException e) {
                System.err.println("소켓 닫기 실패: " + e.getMessage());
            }
        }
    }

    static class DataInputTask implements Runnable {
        private final Socket socket;

        DataInputTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
                while (!socket.isClosed()) {
                    String message = inputStream.readUTF();
                    log("클라이언트 메시지: " + message);
                }
            } catch (IOException e) {
                System.err.println("DataInputTask 종료: " + e.getMessage());
            }
        }
    }

    static class DataOutputTask implements Runnable {
        private final Socket socket;
        private final Scanner scanner = new Scanner(System.in);

        DataOutputTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                while (!socket.isClosed()) {
                    String message = scanner.nextLine();
                    dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();

                    if (message.equals("/exit")) {
                        log("클라이언트 종료 요청");
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("DataOutputTask 종료: " + e.getMessage());
            }
        }
    }
}
