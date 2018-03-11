package study.stream;

import pojo.Person;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
//        test4();
//        test5();
//        test6();
//        test7();
        test10();
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

    //
    static void test4() {
        String phrase = persons
                .stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getName()) // p가 person객체가 아닌 string이 됨 p.getName()이게 곧 리턴문이기 때문에
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
    }

    //custom map
    //Collector를 만들기 위해선, 반드시 supplier, accumulator, combiner, finisher를 컬렉션의 네 가지 재료 넘겨줘야 한다.
    static void test5() {
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String result = persons.stream().collect(personNameCollector);
        System.out.println("result:"+result);
    }

    //감소 연산은 스트림의 모든 원소들을 하나의 결과로 합친다. Java 8 는 세 가지 다른 reduce 방법을 제공한다.
    //1. reduce(BinaryOperator<T> binaryOperator)
    //2. reduce(Objecd
    static void test6() {
        System.out.println("DeepenConcept.test6");
        System.out.println("가장 나이 많은 사람");
        persons.stream()
                .reduce((p1,p2)-> p1.getAge()<p2.getAge()?p2:p1 )
                .ifPresent(System.out::println);

        System.out.println("가장 나이 적은 사람");
        persons.stream()
                .reduce((p1,p2)->p1.getAge()<p2.getAge() ? p2:p1)
                .ifPresent(System.out::println);
    }

    //reduce 두 번째 방법
    //항등값과 BinaryOperator accumulator를 받는다.
    //이 메서드는 스트림에 있는 모든 사람들의 이름을 이어붙인 문자열과 나이를 전부 더한 값을 가지는 Person을 생성하는데 활용할 수 있다.
    static void test7() {
        System.out.println("DeepenConcept.test7");
        Person result = persons.stream()
                .reduce(new Person("test",0), (p1,p2)->{
                    p1.setName(p1.getName()+p2.getName());
                    p1.setAge(p1.getAge()+p2.getAge());
                    return p1;
                });
        System.out.println("result name:"+result.getName()+", result age:"+result.getAge());
    }

    //reduce 세번째 방법
    //세 번째 reduce 메서드는 세 개의 매개변수를 받는다.
    // 항등값, BiFunction accumulator 그리고 BinaryOperator 타입의 combiner 함수.
    // 항등값의 타입이 Person 으로 제한되지 않기 때문에 우리는 모든 사람들의 나이의 합을 알아내는데 이 감소연산을 활용할 수 있다.
    static void test8() {
        System.out.println("DeepenConcept.test8");
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.getAge(), (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);

        ageSum = persons.stream()
                .reduce(0,
                        (sum,p)->{
                            System.out.println("sum = " + sum+",person:"+p);;
                            return sum+=p.getAge();
                            },

                        (sum1, sum2)->{
                            System.out.println("sum1 = " + sum1+", sum2="+sum2);
                            return sum1+sum2;
                        });
    }

    static void test9() {

    }

    //map reduce 공부
    //오늘 이거 보기 http://iloveulhj.github.io/posts/java/java-stream-api.html
    static void test10() {
        persons.stream().reduce(0,
                (sum, p)->{
                    System.out.println("accum sum:"+ sum+", person:"+p);
                    return sum += p.getAge();
                },
                (sum1, sum2)->{
                    //이게 출력이 안됨
                    System.out.println("combiner sum1:"+sum1+", sum2:"+sum2);
                    return sum1 +sum2;
                });
        System.out.println("pararell stream------------------------");
        persons.parallelStream().reduce(0,
                (sum, p)->{
                    System.out.println("accum sum:"+ sum+", person:"+p);
                    return sum += p.getAge();
                },
                (sum1, sum2)->{
                    //이게 출력이 안됨
                    System.out.println("combiner sum1:"+sum1+", sum2:"+sum2);
                    return sum1 +sum2;
                });
        //근데 실전에서는 mapToInt를 마니 사용한다.
        int result = persons.stream().mapToInt(p->p.getAge()).sum();
        System.out.println("maptoint result:"+result);
    }
}
