package study.lambda;

/**
 * Created by Nano.son on 2018. 3. 3.te
 */
public class LambdaScopeTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Lambda4 lambda4 = new Lambda4();
        lambda4.testScopes();
    }
}

class Lambda4 {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerNum = from;
            outerStaticNum = 72;
            return String.valueOf(from);
        };

        System.out.println("outerNum = " + outerNum);
        System.out.println(stringConverter1.convert(33));
        System.out.println("outerNum = " + outerNum);
        System.out.println(stringConverter2.convert(21));
        System.out.println("outerNum = " + outerNum);
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

