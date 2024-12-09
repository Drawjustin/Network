package was.v8;

import was.httpserver.HttpServer;
import was.httpserver.HttpServlet;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV1;
import was.httpserver.servlet.annotation.AnnotationServletV2;
import was.httpserver.servlet.annotation.AnnotationServletV3;

import java.io.IOException;
import java.util.List;

public class ServerMainV8 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteControllerV8(), new SearchControllerV8());
//        HttpServlet annotationServletV1 = new AnnotationServletV2(controllers);
        HttpServlet annotationServletV1 = new AnnotationServletV3(controllers);
        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServletV1);
        servletManager.add("/favicon.ico",new DiscardServlet());
        HttpServer server = new HttpServer(PORT,servletManager);
        server.start();
    }
}
