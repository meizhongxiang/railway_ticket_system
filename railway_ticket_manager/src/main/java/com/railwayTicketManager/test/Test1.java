package com.railwayTicketManager.test;

/**
 * @Author: meizx
 * @ClassName: Test1
 * @Date: 2020/1/7 23:15
 * @E-mail： meizxbj@gmail.com
 * @Description: Test1
 * @Version: V1.0.0
 */
public class Test1 {
    public static void main(String[] args) {
        int year = 0;
        //int year = 0;
        double incr = 0.1, begin = 1.0, end = 2.0;
        while(begin < end){
            begin = (1 + incr) * begin;
            //year++;
            System.out.println("第"+(++year)+"达到"+String.format("%.2f", begin));
            //System.out.println("第"+year+"达到"+String.format("%.2f", begin));
        }
        System.out.println("共"+year+"年实现总量翻两翻");
    }
}
