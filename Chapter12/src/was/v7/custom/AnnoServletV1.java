package was.v7.custom;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static util.MyLogger.log;

public class AnnoServletV1 implements HttpServlet {

    private final List<Object> controllers;

    public AnnoServletV1(List<Object> controller) {
        this.controllers = controller;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();
        Method[] declaredMethods = CustomController.class.getDeclaredMethods();
        for (Object controller : controllers) {
            for (Method declaredMethod : declaredMethods) {
                ClassNameAnno anno = declaredMethod.getAnnotation(ClassNameAnno.class);
                if(anno.name().equals(path)){
                    invoke(controller,declaredMethod,request,response);
                    return;
                }
            }
        }
        throw  new PageNotFoundException("request = " + path);
    }



    public static void invoke(Object controller, Method declaredMethods, HttpRequest request, HttpResponse response){
        try {
            declaredMethods.invoke(controller,request,response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log(e);
        }
    }
}
