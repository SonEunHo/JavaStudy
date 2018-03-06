package study.stream;

import pojo.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nano.son on 2018. 3. 4.
 */
public class DeepenConcept {
    private static List<Person> persons;
    static{
        persons = new ArrayList<>();
        persons.add(new Person("세종대왕", 18));
        persons.add(new Person("임꺽정", 32));
        persons.add(new Person("홍길동", 29));
        persons.add(new Person("이순신", 30));
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    //Collect연산
    //List, Set, Map 같은 자료구조를 반환하는데 유용하다
    //collect 단말 연산을 통해서 손쉽게 자료구조를 만들어 낼 수 있는데,
    //이 메소드는 Collector 인스턴스를 받는다. 이에 대한 내용은 doc 참고
    static void test1() {
        List<Person> personList;
        Stream<Person> stream = persons.stream();
        personList = stream.collect(Collectors.toList());
        personList.forEach(System.out::println);
        //아래는 에러가 나야 정상이다. 단말 연산을 수행한 스트림은 재사용이 불가능하다.
        stream.forEach(System.out::println);
    }

    static void test2() {
        Map<Integer, List<Person>> pMap;
        Stream<Person> stream = Stream.of(new Person("김두한", 23),
                new Person("세종대왕", 18),
                new Person("임꺽정", 29),
                new Person("홍길동", 29),
                new Person("이순신", 30));
        pMap = stream.collect(Collectors.groupingBy(p->p.getAge()));
        pMap.forEach((age, list)-> System.out.println("age= " + age+", list:"+list));
    }

    //평균 및 통계
    static void test3() {
        Double ageAver = persons.stream().collect(Collectors.averagingInt(p->p.getAge()));
        System.out.println("ageAver = " + ageAver);

        IntSummaryStatistics statistics = persons.stream().collect(Collectors.summarizingInt(p->p.getAge()));
        System.out.println("statistics = " + statistics);
    }

    static void test4() {
        String phrase = persons
                .stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getName()) // p가 person객체가 아닌 string이 됨 p.getName()이게 곧 리턴문이기 때문에
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
    }

    //custom map
    static void test5() {

    }
}
