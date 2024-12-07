package chatprogram.server;

import chatprogram.config.ServerConfig;
import chatprogram.session.DataStreamHandler;
import chatprogram.session.Session;
import chatprogram.session.SessionManager;
import chatprogram.session.StreamHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int SERVER_PORT = ServerConfig.SERVER_PORT;
    private static final StreamHandler streamHandler = ServerConfig.getStreamHandler();
    private static final SessionManager sessionManager = ServerConfig.getSessionManager();

    public static void main(String[] args) {
        try {
            while(true) {
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                Socket socket = serverSocket.accept();
                new Thread(new Session(socket, sessionManager, streamHandler)).start();
            }

        }catch (Exception e){

        }
    }

}
