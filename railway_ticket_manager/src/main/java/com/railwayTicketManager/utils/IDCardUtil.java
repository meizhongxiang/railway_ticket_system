package com.railwayTicketManager.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: meizx
 * @ClassName: IDCardUtil
 * @Date: 2019/12/9 12:34
 * @E-mail： meizxbj@gmail.com
 * @Description: IDCardUtil
 * @Version: V1.0.0
 */
public class IDCardUtil {
    private static final Map<Integer, String> map = new HashMap<>();
    static{
        map.put(0, "1");
        map.put(1, "0");
        map.put(2, "X");
        map.put(3, "9");
        map.put(4, "8");
        map.put(5, "7");
        map.put(6, "6");
        map.put(7, "5");
        map.put(8, "4");
        map.put(9, "3");
        map.put(10, "2");
    }
    /*public static void main(String[] args) {
        checkIDCard("401225199612021586");
    }*/
    public static boolean checkIDCard(String card){
        String last = card.substring(17,18);
        int sum=0;
        int[] temp = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int[] num = new int[card.length()-1];
        for (int i = 0; i < card.length()-1; i++) {
            num[i] = Integer.parseInt(card.substring(i,i+1));
            num[i]=temp[i]*num[i];
            sum+=num[i];
        }
        if(last.equals(map.get(sum % 11))){
            //System.out.println("正确");
            return true;
        }else{
            //System.out.println("不正确");
            return false;

        }
    }

}
