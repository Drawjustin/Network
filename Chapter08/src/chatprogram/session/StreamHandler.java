package chatprogram.session;

import java.io.*;

public interface StreamHandler {
    void handle(InputStream inputStream, OutputStream outputStream) throws IOException;
}
