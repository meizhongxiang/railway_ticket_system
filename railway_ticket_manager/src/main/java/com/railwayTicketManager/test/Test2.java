package com.railwayTicketManager.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: meizx
 * @ClassName: Test2
 * @Date: 2020/1/7 23:26
 * @E-mail： meizxbj@gmail.com
 * @Description: Test2
 * @Version: V1.0.0
 */
public class Test2 {
    public static void main(String[] args) {
        int len = 0;
        try{
            File fin = new File("d:\\text.txt");
            //File fout = new File("d:\\text.bak");
            if(fin.exists()){
                FileInputStream fis = new FileInputStream(fin);
                FileOutputStream fos = new FileOutputStream(new File("d:\\text.bak"));
                //FileOutputStream fos = new FileOutputStream(fout);
                len = (int)fin.length();
                byte[] b = new byte[len];
                int i = 0;
                while((i = fis.read(b,0,len)) != -1){
                    fos.write(new String(b).getBytes());
                }
                fos.close();
                fis.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
