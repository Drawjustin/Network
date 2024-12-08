package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.*;

public class HttpRequest {
    private String method;
    private String path;
    // q=hello&key2=value2
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String,String> headers = new HashMap<>();

    public HttpRequest(BufferedReader br) throws IOException {
        parseRequestLine(br);
        parseHeaders(br);
        // 메시지 바디는 이후에 처리
    }

    // GET /search?q=hello HTTP/1.1
    // Host: localhost:12345
    private void parseRequestLine(BufferedReader br) throws IOException {
        String requestLine = br.readLine();
        if(requestLine == null){
            throw new IOException("EOF: No request line received");
        }
        String[] parts = requestLine.split(" ");
        if(parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }

        String method = parts[0];
        // /search?q=hello
        String[] pathParts = parts[1].split("\\?");
        path = pathParts[0];
        if(pathParts.length>1){
            parseQueryParameters(pathParts[1]);
        }

    }

    private void parseQueryParameters(String queryString) {
        for (String param : queryString.split("&")) {
            String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], UTF_8) : "";
            queryParameters.put(key,value);
        }
    }

    private void parseHeaders(BufferedReader br) throws IOException {
        String line;
        while(!(line = br.readLine()).isEmpty()){
            String[] headerParts = line.split(":");
            // trim() 앞 뒤 공백 제거
            headers.put(headerParts[0].trim(),headerParts[1].trim());

        }

    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name){
        return queryParameters.get(name);
    }

    public String getHeader(String name){
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}
