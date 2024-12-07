//package chatprogram.factory;
//
//import chatprogram.session.DataStreamHandler;
//import chatprogram.session.StreamHandler;
//
//public class StreamHandlerFactory {
//    public static StreamHandler createStreamHandler(String type) {
//        switch (type.toLowerCase()) {
//            case "json":
//                return new JsonStreamHandler();
//            case "xml":
//                return new XmlStreamHandler();
//            case "data":
//            default:
//                return new DataStreamHandler();
//        }
//    }
//}
//
