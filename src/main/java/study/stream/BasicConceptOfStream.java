package study.stream;

import pojo.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Nano.son on 2018. 3. 2.
 * map, filter, foreach, sorted 와 같은 기본적인 메소드 공부
 * stream의 기본적인 특성을 살펴봄
 */
public class BasicConceptOfStream {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
        test11();
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
        list.stream().sorted((p1,p2)-> -p1.getName().compareTo(p2.getName())).forEach(System.out::println);
        System.out.println("나이 순서");
        //아 이거 그냉 p1.getAge() - p2.getAge() 하면 되네
        list.stream().sorted((p1,p2)-> (p1.getAge()<p2.getAge())?-1:(p1.getAge()==p2.getAge()?0:1)).forEach(System.out::println);
    }

    //stream을 사용한다고 해서 기존 리스트가 변경되지는 않는다.
    static void test6() {
        System.out.println("Main.test6");
        List<String> list = Arrays.asList("hello","world","nano","kakao");
        System.out.println("stream 연산");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("list 변경확인");
        list.forEach(System.out::println);
    }

    //Stream 생성
    //IntStream, DoubleStream, LongStream 와 같은 것들이 원시 스트림이라고 불린다.
    static void test7() {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("----------------------------");
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("----------------------------");
        IntStream.range(1,4)
                .forEach(System.out::println);

        System.out.println("----------------------------");

    }

    //중간연산은 단말연산이 있어야 실행된다.
    //filter가 모두 실행되고 forEach가 실행될 거라고 생각했다.
    //하지만 하나의 요소에 대하여 중간, 단말 모두 수행 뒤 다른 요소의 순서가 됐다,.
    public static void test8() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    // 동작은 각각의 원소에 대해 실제로 실행되는 연산의 수를 줄여줄 수 있다.
    // 스트림 연결은 수직적으로 실행된다??
    public static void test9() {
        System.out.println("Main.test9");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    //filter를 맨 앞으로 당기는 것으로 연산의 순서를 바꾼다면, 실제로 실행되는 연산의 수를 줄일 수 있다.
    //filter가 불릴 거라면 미리 부르는게 좋다.
    public static void test10() {
        System.out.println("Main.test10");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        // map:     d2
        // filter:  D2
        // map:     a2
        // filter:  A2
        // forEach: A2
        // map:     b1
        // filter:  B1
        // map:     b3
        // filter:  B3
        // map:     c
        // filter:  C

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

//        filter: d2
//        filter: a2
//        map: a2
//        forEach: A2
//        filter: b1
//        filter: b3
//        filter: c
    }

    //stream은 재사용이 불가능하다.
    //단말 연산을 수행한 스트림은 더이상 다른 일을 못하낟.
    //Exception in thread "main" java.lang.IllegalStateException:
    // stream has already been operated upon or closed
    public static void test11() {
        System.out.println("Main.test11");
        Stream stream = Stream.of("hello","world","nano","son").filter(e->e.length()>3);
        stream.forEach(System.out::println);
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
        stream.forEach(System.out::println);
    }
}
