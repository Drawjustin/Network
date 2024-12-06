package chatprogram.server;

import chatprogram.session.InputSession;
import chatprogram.session.OutputSession;
import chatprogram.session.SessionManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static chatprogram.util.MyLogger.log;

public class Server {
    private static final int SERVER_PORT = 12345;
    private static final SessionManager sessionManager = new SessionManager();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            log("Server Host Open : " + SERVER_PORT);
            while(true) {
                Socket socket = serverSocket.accept();
                Thread inputSession = new Thread(new InputSession(socket));
                Thread outputSession = new Thread(new OutputSession(socket));
                sessionManager.addInputSession(new InputSession(socket));
                sessionManager.addOutputSession(new OutputSession(socket));
                inputSession.start();
                outputSession.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
