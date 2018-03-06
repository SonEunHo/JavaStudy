package study.serializableStudy;

import java.io.*;

public class Main {

    public static void main(String[] args){
        Friend friend1 = new Friend(20, "홍길동", "010-1111-2345");
        Friend friend2 = new Friend(24, "이순신", "010-3333-7777");

        //write objects to file
        File file = new File("test.txt");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream oos = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(friend1);
            oos.writeObject(friend2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fileOutputStream.close();
            } catch (Exception e) { }
        }

        //read objects from file
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            Friend friend;

            friend= (Friend) objectInputStream.readObject();
            System.out.println("age :"+friend.getAge() + ", name:"+friend.getName() + ", phoneNum:"+friend.getPhoneNumber());
            friend= (Friend) objectInputStream.readObject();
            System.out.println("age :"+friend.getAge() + ", name:"+friend.getName() + ", phoneNum:"+friend.getPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (Exception e) { }
        }
    }
}


