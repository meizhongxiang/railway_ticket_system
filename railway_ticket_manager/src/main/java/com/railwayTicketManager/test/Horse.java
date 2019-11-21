package com.railwayTicketManager.test;

/**
 * @Author: meizx
 * @ClassName: Test6
 * @Date: 2020/1/8 0:08
 * @E-mail： meizxbj@gmail.com
 * @Description: Test6
 * @Version: V1.0.0
 */
public class Horse implements ClassName{
    public static void main(String[] args) {
        System.out.println("类名" + new Horse().getClassName());
    }
    public String getClassName() {
        //输出 Horse
        return this.getClass().getSimpleName();
        //输出 com.railwayTicketManager.test.Horse
        //return this.getClass().getName();
    }
}

interface ClassName {
    String getClassName();
}
