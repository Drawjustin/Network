package chat.server.command;

import chat.server.Session;

import java.io.IOException;

public interface Command {
//    static boolean validateArgs(String[] args) {
//        return args.length > 1; // 기본적으로 매개변수 검증을 통과
//    }
    void execute(String[] args, Session session) throws IOException;

}
