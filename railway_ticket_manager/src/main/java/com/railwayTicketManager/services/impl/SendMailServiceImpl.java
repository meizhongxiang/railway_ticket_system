package com.railwayTicketManager.services.impl;


import com.railwayTicketManager.config.RedisClient;
import com.railwayTicketManager.config.sendMail.mail.EmailInfo;
import com.railwayTicketManager.config.sendMail.mail.EmailText;
import com.railwayTicketManager.config.sendMail.mail.EmailUtil;
import com.railwayTicketManager.services.SendMailService;
import com.railwayTicketManager.services.VerificationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: meizx
 * @ClassName: SendMailServiceImpl
 * @Date: 2019-07-01 09:53
 * @E-mail： meizxbj@gmail.com
 * @Description:
 * @Version: V1.0.0
 */
@Service
public class SendMailServiceImpl implements SendMailService {
    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);

    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private RedisClient redisClient;
    ;

    @Override
    @Async
    public Boolean sendMail(String mail, String subject, int n, int param) {

        try {
            if (n > 10) {
                n = 10;
            }
            String yzm = verificationCodeService.verificationCode(n, 1);
            redisClient.setCode(mail, yzm, 900);
            EmailText txt = new EmailText();
            EmailUtil util = new EmailUtil();
            EmailInfo info = new EmailInfo(mail, subject, txt.getTxt(yzm, param));
            if (util.sendHtmlMail(info)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            logger.error("====邮件发送失败====");
            return false;
        }
    }

    @Async
    public Boolean expectionMail(String mail, String subject, String stackTrace, String message) {

        try {
            EmailText txt = new EmailText();
            EmailUtil util = new EmailUtil();
            EmailInfo info = new EmailInfo(mail, subject, txt.getExceptionTxt(LocalDateTime.now(), stackTrace, message));
            if (util.sendHtmlMail(info)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            logger.error("====邮件发送失败====");
            return false;
        }
    }

    @Async
    public Boolean sendPayMail(String mail, String subject, String text, int param){
        try {
            EmailText txt = new EmailText();
            EmailUtil util = new EmailUtil();
            EmailInfo info = null;
            //0 退票  1已出票  2待出票  3改签状态  4改签待出票  5改签已出票
            if(param == 0){
                info = new EmailInfo(mail, "退票通知", txt.getCancelPayTxt(text));
            }else if(param == 2){
                info = new EmailInfo(mail, "购票通知", txt.getPayTxt(text));
            }else if(param == 4){
                info = new EmailInfo(mail, "车票改签通知", txt.getUpdatePayTxt(text));
            }else{}

            if (util.sendHtmlMail(info)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            logger.error("====邮件发送失败====");
            return false;
        }
    }
}
