package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) {
        TestController testController = new TestController();
        Class<? extends TestController> aClass = testController.getClass();
        for (Method method : aClass.getDeclaredMethods()) {
            System.out.println("method = " + method);
            SimpleMapping annotation = method.getAnnotation(SimpleMapping.class);
            if(annotation != null) {
                System.out.println("annotation = " + annotation.value());
            }
        }


    }
}
