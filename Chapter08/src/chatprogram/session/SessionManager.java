package chatprogram.session;

public class SessionManager {
    private static final SessionManager sessionManager = new SessionManager();
    private SessionManager() {

    }

    public static SessionManager getSessionManager(){
        return sessionManager;
    }
}
