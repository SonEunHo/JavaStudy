package study.object_method;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nano.son on 2018. 3. 19.
 */
public class HashTest {
    public static void main(String[] args) {
        test_no_hashcode();
    }

    public static void test_no_hashcode() {
        Person p1 = new Person(21, "dy");
        Person p2 = new Person(21, "dy");
        System.out.println(p1.equals(p2));
        Map<Person, String> map = new HashMap<>();

        map.put(p1, "dy");
        map.put(p2, "dz");
        System.out.println(map.size());

        System.out.println(map.get(p1));
        System.out.println(map.get(p2));

    }


}
