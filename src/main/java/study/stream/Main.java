package study.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Nano.son on 2018. 3. 2.
 */
public class Main {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    //enhanced for, foreach는 수정 불가능
    static void test1() {
        System.out.println("Main.test1");

        List<String> list = Arrays.asList("hello","world","nano","kakao");
        for (String s : list) {
            s = s.toUpperCase();
            //System.out.println("s = " + s);
        }

        for (String s : list) {
            System.out.println("s = " + s);
        }
    }

    static void test2() {
        System.out.println("Main.test2");

        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("nano");
        list.add("kakao");

        for (String s : list) s = s.toUpperCase();
        for (String s : list) System.out.println("s = " + s);
    }

    static void test3() {
        System.out.println("Main.test3");

        List<String> list = Arrays.asList("hello","world","nano","kakao");
        list.forEach(e->e=e.toUpperCase());
        for (String s : list) System.out.println("s = " + s);
    }

    static void test4() {
        System.out.println("Main.test4");
        List<String> list = Arrays.asList("hello","world","nano","kakao");

        list.stream()
                .map(e->e=e.toUpperCase())
                .sorted()
                .forEach(System.out::println);
        //map 으로 문자열을 대문자로 바꿈
        //sorted 로 리스트의 문자들을 오름차순으로 바꿈
        //forEach & 메소드 참조(함수형 프로그래밍) 로 각 문자 출력
    }

    static void test5() {
        System.out.println("Main.test5");
        List<Person> list = new ArrayList<>();
        list.add(new Person("홍길동", 24));
        list.add(new Person("김철수", 20));
        list.add(new Person("박명수", 28));
        list.add(new Person("권혁수", 30));

        System.out.println("이름 역순");
        list.stream().sorted((p1,p2)-> -p1.name.compareTo(p2.name)).forEach(System.out::println);
        System.out.println("나이 순서");
        list.stream().sorted((p1,p2)-> (p1.age<p2.age)?-1:(p1.age==p2.age?0:1)).forEach(System.out::println);
    }
}

class Person {
    String name;
    int age;

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
}
