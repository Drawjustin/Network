package chat.server;

import chat.util.SocketCloseUtil;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static chat.util.MyLogger.log;

public class Session implements Runnable{
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private boolean closed = false;
    private String username;

    public Session(Socket socket, CommandManager commandManager, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while(true){
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();
                log("client -> server : " + received);

                //메세지를 전체에게 보내기
                commandManager.execute(received,this);
            }
        }catch (IOException e){
            log(e);
        }finally {
            sessionManager.remove(this);
            sessionManager.sendAll(username + "님이 퇴장했습니다");
            close();
        }
    }

    public void send(String message) throws IOException {
        log("server -> client: " + message);
        output.writeUTF(message);
    }


    public void close() {
        if(closed){
            return;
        }
        SocketCloseUtil.closeAll(socket,input,output);
        closed = true;
        log("연결 종료 " + socket);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }









}
