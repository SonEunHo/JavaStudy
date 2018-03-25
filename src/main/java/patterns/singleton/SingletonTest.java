package patterns.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by Nano.son on 2018. 3. 15.
 */
public class SingletonTest {
    public static void main(String[] args) {
        SingletonClass s1 = SingletonClass.getInstance();
        SingletonClass s2 = null;
        SingletonClass s3 = SingletonClass.getInstance();

        //자바 reflection을 통해서 다음과 같이 private 생성자를 둔 클래스의 인스턴스를 생성할 수 있다.
        //물론 이번 예제에서는 private 생성자에서 플래그를 사용하여 싱글톤을 만들어낸 후 생성자가 호출되면 에러를 발생시키도록 했다.
        try {
            Constructor<SingletonClass> constructor = SingletonClass.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            s2 = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        Boolean.valueOf("true");
    }
}
