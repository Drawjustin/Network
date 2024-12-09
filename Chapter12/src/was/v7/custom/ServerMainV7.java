package was.v7.custom;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;

import java.io.IOException;
import java.util.List;

public class ServerMainV7 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {

        List<Object> controllers = List.of(new CustomController());
        AnnoServletV1 annoServlet = new AnnoServletV1(controllers);
        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annoServlet);
        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();

    }
}
