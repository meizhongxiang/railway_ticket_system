package com.railwayTicketManager.services;

import org.springframework.scheduling.annotation.Async;

/**
 * @Author: meizx
 * @ClassName: SendMailService
 * @Date: 2019/11/27 23:34
 * @E-mail： meizxbj@gmail.com
 * @Description: SendMailService
 * @Version: V1.0.0
 */
public interface SendMailService {
    public Boolean sendMail(String mail ,String subject, int n,int param);
    public Boolean expectionMail(String mail, String subject, String stackTrace, String message);
    public Boolean sendPayMail(String mail, String subject, String text, int param);
}
