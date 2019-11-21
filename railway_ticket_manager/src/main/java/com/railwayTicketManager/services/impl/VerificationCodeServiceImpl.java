package com.railwayTicketManager.services.impl;

import com.railwayTicketManager.services.VerificationCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author: meizx
 * @ClassName: VerificationCodeServiceImpl
 * @Date: 2019-07-01 10:15
 * @E-mail： meizxbj@gmail.com
 * @Description: n  需要的长度  param:1数字或者0字母数字组合
 * @Version: V1.0.0
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Override
    public String verificationCode(int n, int param) {
        /**
         * 生成随机数
         */
        String val = "";
        Random random = new Random();
        if(param == 1){
            for (int i = 0; i < n; i++) {
                val += String.valueOf(random.nextInt(10));
            }
        }else{
            for (int i = 0; i < n; i++) {
                String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
                if ("char".equalsIgnoreCase(str)) { // 产生字母
                    int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                    val += (char) (nextInt + random.nextInt(26));
                } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                    val += String.valueOf(random.nextInt(10));
                }
            }
        }
        return val;
    }
}
