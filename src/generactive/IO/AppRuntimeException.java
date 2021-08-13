package generactive.IO;

public class AppRuntimeException extends RuntimeException{
     public AppRuntimeException(){
         super("can't read the file");
     }
}
