package patterns.StrategyPattern;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nano.son on 2018. 3. 25.
 */
public class StrategyPatternMain {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 대표적인 예로 Comparator가 있다.
     * 대부분 아래 코드 처럼 익명 내부 클래스를 생성하여 전달하는 방식이라서, 사용할 때 마다 새로운 객체를 생성한다.
     * 자주 쓰는 것은 하나만 만들어서 재사용 하면 된다.
     * 대표적인게 String.CASE_INSENSITIVE_ORDER 가 있다. (궁금하면 코드 뜯어봐라)
     */
    public static void test1() {
        String[] arr = {"hello", "nano", "abc", "aba"};
        Arrays.sort(arr, (s1, s2)->s1.compareTo(s2));
        Arrays.stream(arr).forEach(System.out::println);

        System.out.println("------------------------");
        Arrays.sort(arr, (s1, s2)->-(s1.compareTo(s2)));
        Arrays.asList(arr).stream().forEach(e-> System.out.println(e));
    }

    /**
     * BackGroundManager는 어떤 작업을 실행할 것인지 결정하는 객체를 주입받는다.
     * 실행 전, 후 어떤 작업을 할 지 결정하는 함수형 인터페이스 구현체를 주입받는다.
     * backGroundManager는 불변객체가 아니다. builder로 설정해둔 함수형 객체들을 참조하는 변수를 갖고 있다면 변경가능하기 때문이다.
     * 만약 불변 객체로 만들고 싶다면 Builder를 불변객체로 하던가, 혹은 BackGroundManager에 setter에서 깊은 복사를 해야한다.
     */
    public static void test2() {
        BackGroundManager.Builder builder =
                new BackGroundManager.Builder(()-> System.out.println("this is backGroundTask1"))
                .beforeTask(()-> System.out.println("before task!!!"))
                .afterTask(()-> System.out.println("after task!!!"));
        BackGroundManager backGroundManager = builder.build();

        backGroundManager.run();
    }
}
