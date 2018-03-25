package study.finalize;

import java.io.*;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Nano.son on 2018. 3. 18.
 */
public class FinalizeTest {
    public static void main(String[] args) {
//        test_finalize();
//        test_close();
//        test_try_with_resource();
        test_try_with_resource2();
    }

    public static void test_finalize() {
        Person p1 = new Person(20, "scott");
        Person p2= new Person(23, "dy");
        Person p3 = new Person(21, "nano");
        Person p4 = new Person(21, "sans");
        p1 = p2 = p3 = p4 = null;
        System.gc();
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//
//        }
        System.runFinalization();
    }

    /**
     * 명시적 종료 메소드는 try-finally문과 함께 쓰인다.
     * */
    public static void test_close() {
        File file = new File("fileTest.txt");
        FileOutputStream fileOutputStream = null;
        BufferedReader in = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("30\n".getBytes());
            fileOutputStream.write("20\n".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            String line=null;
            in = new BufferedReader(new FileReader(file));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
//            fileOutputStream.getChannel().isOpen();
            in.close();
        } catch (Exception e) {

        } finally {
            try {
                fileOutputStream.close();
                in.close();
            } catch (Exception e) {
                System.err.println("close 실패");
                e.printStackTrace();
            }
        }
    }

    /**
     * Note:
     * A try-with-resources statement can have catch and finally blocks just like an ordinary try statement.
     * In a try-with-resources statement, any catch or finally block is run after the resources declared have been closed.
     * */
    public static void test_try_with_resource() {
        File file = new File("fileTest.txt");

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedReader in = new BufferedReader(new FileReader(file))
        ){
            fileOutputStream.write("30\n".getBytes());
            fileOutputStream.write("20\n".getBytes());
            fileOutputStream.write("10\n".getBytes());
            fileOutputStream.write("0\n".getBytes());
//            fileOutputStream.flush();

            String line=null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test_try_with_resource2() {
        try (
                MyStream myStream = new MyStream();
                AutoCloseable a = ()->{System.out.println("a.close");}
                ) {
            System.out.println("***1");
            System.out.println("***2");
            System.out.println("***3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("***4");
    }

    public static void test_finalizer_Guardian() {

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
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Person.finalize name:"+name);
    }

    private final Object finalizerGuardian = new Object() {
        @Override
        protected void finalize() throws Throwable {
            Person.this.finalize();
        }
    };


}