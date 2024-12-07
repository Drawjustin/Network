package chatprogram.config;

import chatprogram.session.DataStreamHandler;
import chatprogram.session.SessionManager;
import chatprogram.session.StreamHandler;

public class ServerConfig {
    public static final int SERVER_PORT = 12345;

    public static SessionManager getSessionManager(){
        return SessionManager.getSessionManager();
    }
    public static StreamHandler getStreamHandler(){
        return DataStreamHandler.getStreamHandler();
    }
}
