package com.railwayTicketManager.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: meizx
 * @ClassName: Test5
 * @Date: 2020/1/8 0:07
 * @E-mail： meizxbj@gmail.com
 * @Description: Test5
 * @Version: V1.0.0
 */
public class Test5 {
    public static void main(String[] args) {
        int len = 0;
        try{
            File fin = new File("d:\\text.txt");
            if(fin.exists()){
                FileInputStream fis = new FileInputStream(fin);
                len = (int)fin.length();
                byte[] b = new byte[len];
                int i = 0;
                while((i = fis.read(b,0,len)) != -1){
                    System.out.println(new String(b));
                }
                fis.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
