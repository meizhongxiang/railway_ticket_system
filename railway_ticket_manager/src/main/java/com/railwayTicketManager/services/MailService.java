package com.railwayTicketManager.services;

import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.dao.po.SaleTicketPO;

/**
 * @Author: meizx
 * @ClassName: MailService
 * @Date: 2019/11/28 22:51
 * @E-mail： meizxbj@gmail.com
 * @Description: MailService
 * @Version: V1.0.0
 */
public interface MailService {
    RspInfoBO sendMail(String mail);

    void sendExpectionMail(String mail,String stackTrace, String message);

    void sendPayMail(String mail, SaleTicketPO saleTicketPO, int param);
}
