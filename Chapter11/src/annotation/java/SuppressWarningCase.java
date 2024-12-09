package annotation.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuppressWarningCase {
    @SuppressWarnings("unused")
    public void unusedWarning(){
        // 사용되지 않는 변수 경고 억제
        int unusedVariable = 10;

    }
    @SuppressWarnings("deprecation")
    public void deprecatedMethod(){
        // 더이상 사용되지 않는 메서드 호출
        Date date = new Date();
        int date1 = date.getDate();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void uncheckedCast(){
        // 제네릭 타입 캐스팅 경고 억제, raw type 사용 경고
        List list = new ArrayList();
        List<String> stringList = (List<String>)list;
    }

    @SuppressWarnings("all")
    public void suppressAllWarning(){
        Date date = new Date();
        int date1 = date.getDate();
        List list = new ArrayList();
        List<String> stringList = (List<String>)list;
    }



}