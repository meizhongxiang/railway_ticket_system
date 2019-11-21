package com.railwayTicketManager.services.impl;

import com.railwayTicketManager.baseBO.Constants;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.dao.po.SaleTicketPO;
import com.railwayTicketManager.services.MailService;
import com.railwayTicketManager.services.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: meizx
 * @ClassName: MailServiceImpl
 * @Date: 2019/11/28 22:51
 * @E-mail： meizxbj@gmail.com
 * @Description: MailServiceImpl
 * @Version: V1.0.0
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private SendMailService sendMailService;
    @Override
    public RspInfoBO sendMail(String mail) {
        RspInfoBO rinfo=new RspInfoBO();
        try{
            if(!("".equals(mail))) {
                sendMailService.sendMail(mail,"七点：邮箱验证",6,701);
                rinfo.setRspCode(Constants.RSP_CODE_SUCCESS);
                rinfo.setRspDesc(Constants.RSP_DESC_SUCCESS);
            }else{
                rinfo.setRspCode(Constants.RSP_CODE_FAILUR);
                rinfo.setRspDesc(Constants.RSP_DESC_FAILUR);
            }
        }catch (Exception e){
            rinfo.setRspCode(Constants.SYSTEM_EXCEPT_MAIL_CODE);
            rinfo.setRspDesc(Constants.SYSTEM_EXCEPT_MAIL_DESC);
            log.error("=======邮件验证失败=======");
        }
        return rinfo;
    }

    @Override
    public void sendExpectionMail(String mail,String stackTrace, String message) {
        try{
            if(!("".equals(mail))) {
                sendMailService.expectionMail(mail,"系统异常通知",stackTrace, message);
            }else{
            }
        }catch (Exception e){
            log.error("=======邮件验证失败=======");
        }
    }

    public void sendPayMail(String mail, SaleTicketPO saleTicketPO, int param){
        try{
            if(!("".equals(mail))) {
                sendMailService.sendPayMail(mail,"","日期:"+saleTicketPO.getStartDate()+",\n车次:"+saleTicketPO.getTrainNumber()+",\n坐席类别:"+(saleTicketPO.getTicketClass()==1?"一等座":"二等座")+",\n座号:"+saleTicketPO.getTicketNum()+",\n金额:"+saleTicketPO.getPrice(), param);
            }else{
            }
        }catch (Exception e){
            log.error("=======邮件验证失败=======");
        }
    }
}
