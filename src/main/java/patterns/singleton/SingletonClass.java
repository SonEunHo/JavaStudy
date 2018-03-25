package patterns.singleton;

import java.util.stream.IntStream;

/**
 * Created by Nano.son on 2018. 3. 15.
 */
public class SingletonClass {
    private static boolean isCreated = false;
    private static SingletonClass singletonClass = new SingletonClass();

    private SingletonClass() {
        if(isCreated) {
            throw new SingletonBreakException("this is singleton");
        }
        isCreated = true;
    }

    public static SingletonClass getInstance() {
        return singletonClass;
    }
}

class SingletonBreakException extends RuntimeException {
    public SingletonBreakException(String error_msg) {
        super(error_msg);
    }
}

//public static synchronized SingletonClass getInstance() {
//    public static SingletonClass getInstance() {
//        if (singletonClass == null) {
//            singletonClass = new SingletonClass();
//            return singletonClass;
//        }
//        return singletonClass;
//    }

//    IntStream intStream = Stream.of