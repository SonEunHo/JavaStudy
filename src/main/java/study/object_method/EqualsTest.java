package study.object_method;

import java.util.*;

/**
 * Created by Nano.son on 2018. 3. 19.
 */
public class EqualsTest {
    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
    }
    /**
     * 대칭성 위반
     * */
    public static void test1() {
        Point p  = new Point(1,2);
        ColorPoint cp = new ColorPoint(1,2,10);
        System.out.println(p.equals(cp));
        System.out.println(cp.equals(p));
    }

    /**
     * 대칭성 만족 but 추이성 위반
     */
    public static void test2() {
        Point p  = new Point(1,2);
        ColorPoint cp1 = new ColorPoint(1,2,10);
        //대칭성
        System.out.println(p.equals(cp1));
        System.out.println(cp1.equals(p));
        //추이성
        ColorPoint cp2 = new ColorPoint(1,2,11);
        System.out.println(cp1.equals(p));
        System.out.println(p.equals(cp2));
        System.out.println(cp1.equals(cp2));
    }


    /**
     * collection contains
     */
    public static void test3() {
        List<Person> plist = new ArrayList<>();
        Person p1 = new Person(20, "scott");
        Person p2= new Person(23, "dy");
        Person p3 = new Person(21, "nano");
        Person p4 = new Person(21, "sans");

        plist.add(p1);
        plist.add(p2);
        plist.add(p3);
        plist.add(p4);

        if(plist.contains(p1))
            System.out.println("포함");
        else
            System.out.println("미포함");
    }
}


class Person{
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return age == person.age &&
//                Objects.equals(name, person.name);
        return false;
    }

    @Override
    public int hashCode() {

//        return Objects.hash(age, name);
        return 1;
    }
//    @Override
//    public boolean equals(Object obj) {
//        if (obj != null && obj instanceof Person) {
//            Person p = (Person) obj;
//            if(p.age == this.age && p.name.equals(this.name))
//                return true;
//        }
//        return false;
//    }
//
//
//
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31*result + age;
//        result = 31*result + name.hashCode();
//        return result;
//    }
//
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point o = (Point) obj;
            if(this.x == o.x && this.y == o.y)
                return true;
        }
        return false;
    }
}

class ColorPoint extends Point{
    private final int color;
    public ColorPoint(int x, int y, int color) {
        super(x, y);
        this.color = color;
    }

    //대칭성 위반
//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof ColorPoint))
//            return false;
//        return super.equals(obj) && ((ColorPoint) obj).color==color;
//    }

    //추이성 위반
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point))
            return false;
        //Point의 객체이면 color 값은 비교 하지 않음
        if(!(obj instanceof ColorPoint))
            return obj.equals(this);
        return super.equals(obj) && ((ColorPoint) obj).color==color;
    }
}


