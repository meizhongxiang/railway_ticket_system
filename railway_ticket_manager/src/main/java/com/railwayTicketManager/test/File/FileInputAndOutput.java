package com.railwayTicketManager.test.File;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: Meizx
 * @ClassName: File.FileInputAndOutput
 * @Date: 2019-09-06 08:28
 * @E-mail： meizxbj@gmail.com
 * @Description: File.FileInputAndOutput
 * @Version: V1.0.0
 */
public class FileInputAndOutput {
    //抽象类 InputStream 和 OutputStream 构成了输入输出流类的基础



    //处理字节
    /**
     * InputStream 和 OutputStream可以处理单个字节或字节数组，想要读写字符串或数字，就需要更强大的子类，
     * DataInputStream 和 DataOutputStream 可以以二进制形式读写所有的java 基本类型
     * ZipInputStream 和 ZipOutputStream可以以常见的 Zip 压缩格式读写文件
   * */
    public static void byteTest(){
        try {
            DataInputStream din = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("E:\\test.txt")
                    ));
            while(din.read()!=-1){
                System.out.println(din);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    //处理字符
    public static void charTest(){

    }
}
