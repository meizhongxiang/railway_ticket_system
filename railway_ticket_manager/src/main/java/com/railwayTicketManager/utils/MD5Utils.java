package com.railwayTicketManager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: meizx
 * @ClassName: MD5Utils
 * @Date: 2019-07-04 16:35
 * @E-mail： meizxbj@gmail.com
 * @Description:加密
 * @Version: V1.0.0
 */
public class MD5Utils {
    private static final String KEY="meizx";

    public static String md5Encrypt(String str, Long date){
        str = str == null ? "" : str+Long.toString(date)+KEY;
        char[] md5Array ={ '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] strArr = str.getBytes();
            md.update(strArr);
            byte[] md5Str = md.digest();//真正加密

            int len = md5Str.length;
            char[] rStr = new char[len*2];
            int count =0;//计数

            for(int i=0;i <len;i++){
                byte b = md5Str[i];//获得二进制数
                rStr[count++] = md5Array[b >>> 4 & 0xf];
                rStr[count++] = md5Array[b & 0xf];
            }
            return new String(rStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static void main(String[] args) {
        System.out.println(Long.toString(new Date().getTime()));
        System.out.println(MD5Utils.md5Encrypt("hansl@meizx.com"));
    }*/
}
