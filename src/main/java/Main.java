

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        OuterClass p = new OuterClass(20);
        p.hello(10);

        String t= "1516579200000";
        Date date =  new Date(Long.parseLong(t));
        System.out.println(date);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}

class OuterClass {
    private int num;

    public OuterClass(int num) {
        this.num = num;
    }

    public void hello(int param) {

        class LocalClass {
            public void localClassMethod() {
                System.out.println("Ouuter class num:"+num+", param:"+param);
            }
        }
        LocalClass localClass = new LocalClass();
        localClass.localClassMethod();
    }
}

