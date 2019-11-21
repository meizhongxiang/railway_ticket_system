package com.railwayTicketManager.test;

/**
 * @Author: meizx
 * @ClassName: Test3
 * @Date: 2020/1/7 23:52
 * @E-mail： meizxbj@gmail.com
 * @Description: Test3
 * @Version: V1.0.0
 */
public class People {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }
}

class Employee extends People {
    private String empno;
}

class Teacher extends People {
    private String teano;
    private String zc;
}
