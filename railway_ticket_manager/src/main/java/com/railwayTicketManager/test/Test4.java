package com.railwayTicketManager.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: meizx
 * @ClassName: Test4
 * @Date: 2020/1/7 23:57
 * @E-mail： meizxbj@gmail.com
 * @Description: Test4
 * @Version: V1.0.0
 */
public class Test4 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int min = 0, max = 0;
        System.out.println("请输入10个整数，用空格隔开");
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 10; i++){
            a[i] = sc.nextInt();
            if(i == 0){
                min = a[0];
                max = a[0];
            }
            if(min > a[i]){
                min = a[i];
            }
            if(max < a[i]){
                max = a[i];
            }
        }
        System.out.println("数组中的数：" + Arrays.toString(a));
        System.out.println("最大值为：" + max + "\t" + "最小值为：" + min);
        //System.out.println("最大值为：" + max);
        //System.out.println("最小值为：" + min);
    }
}
