package SerializableStudy;

import java.io.Serializable;

public class Friend implements Serializable{
    private int age;
    private String name;
    private String phoneNumber;

    public Friend(int age, String name, String phoneNumber) {
        this.age = age;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Friend() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
