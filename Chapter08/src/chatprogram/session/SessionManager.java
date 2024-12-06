package chatprogram.session;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private final List<InputSession> inputList = new ArrayList<>();
    private final List<OutputSession> outputList = new ArrayList<>();

    public void addInputSession(InputSession session){
        inputList.add(session);
    }
    public void addOutputSession(OutputSession session) {
        outputList.add(session);
    }

    public void closeAll(){
        for (InputSession inputSession : inputList) {
            inputSession.close();
        }
        for (OutputSession outputSession : outputList) {
            outputSession.close();
        }
        inputList.clear();
        outputList.clear();
    }

}
