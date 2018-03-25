package performance_test.finalize;


import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;

/**
 * Created by Nano.son on 2018. 3. 17.
 */
public class FinalizeTest {
    public static void main(String[] args) {
        ArrayList<Person> plist = new ArrayList<>();
        plist.add(new Person("ABC", 23));
        plist.add(new Person("DEF", 25));
        plist.add(new Person("GHI", 21));
        plist.add(new Person("JKL", 20));

        plist.forEach(System.out::println);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        System.out.println(this.name+" finalize!");
//    }
}