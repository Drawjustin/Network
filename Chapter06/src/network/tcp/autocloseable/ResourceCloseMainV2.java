package network.tcp.autocloseable;

public class ResourceCloseMainV2 {
    public static void main(String[] args) {
        try {
            logic();
        }
        catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        }catch(CloseException e){
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CloseException, CallException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try {
            resource1 =new ResourceV1("resourceV1");
            resource2 = new ResourceV1("resourceV2");

            resource1.call();
            resource2.callEx(); // CallException
        }catch (CallException e){
            System.out.println("ex: " + e);
            throw e;
        }finally {
            if(resource2 != null){
                resource2.closeEx(); // CloseException 발생
            }
            if(resource1 != null){
                resource1.closeEx(); // 이 코드 호출 안됨!
            }
        }


    }
}
