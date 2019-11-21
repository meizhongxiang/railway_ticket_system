package com.railwayTicketManager.controller;

import com.railwayTicketManager.api.UserReqBO;
import com.railwayTicketManager.baseBO.RspInfoBO;
import com.railwayTicketManager.services.MailService;
import com.railwayTicketManager.services.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: SendMessageController
 * @Date: 2019/11/28 22:45
 * @E-mail： meizxbj@gmail.com
 * @Description: SendMessageController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class SendMessageController {

    @Autowired
    private MailService mailService;

    /**
     * 1000008：发送邮件
     * */
    @RequestMapping(value = "/1000008", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public RspInfoBO updateInfo(@RequestBody UserReqBO userReqBO) throws Exception{
        RspInfoBO rsp = null;
        try {
            rsp = mailService.sendMail(userReqBO.getAccountId());
        } catch (Exception e) {
            log.error("========UserController---->用户更新/完善实名信息失败========", e);
        }
        return rsp;
    }
}
