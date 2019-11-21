package com.railwayTicketManager.controller;

import com.railwayTicketManager.api.UserReqBO;
import com.railwayTicketManager.api.UserRspBO;
import com.railwayTicketManager.services.UserSercive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: meizx
 * @ClassName: UserController
 * @Date: 2019/11/28 21:33
 * @E-mail： meizxbj@gmail.com
 * @Description: UserController
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class UserController {

    @Autowired
    private UserSercive userSercive;

    /**
     * 1000003：用户快捷注册
     * */
    @RequestMapping(value = "/1000003", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO register(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.register(userReqBO);
        } catch (Exception e) {
            log.error("========UserController---->用户快捷注册失败========", e);
        }
        return rsp;
    }


    /**
     * 1000004：用户完善实名信息
     * */
    @RequestMapping(value = "/1000004", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO realName(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.realName(userReqBO);
        } catch (Exception e) {
            log.error("========UserController---->用户更新/完善实名信息失败========", e);
        }
        return rsp;
    }


    /**
     * 1000005：用户注销账户
     * */
    @RequestMapping(value = "/1000005", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO deleteInfo(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.deleteAccount(userReqBO);
        } catch (Exception e) {
            log.error("========UserController---->用户注销账户失败========", e);
        }
        return rsp;
    }


    /**
     * 1000006：用户个人详情
     * */
    @RequestMapping(value = "/1000006", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO selectInfo(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.personInfo(userReqBO);
        } catch (Exception e) {
            log.error("========UserController---->用户个人详情失败========", e);
        }
        return rsp;
    }

    /**
     * 1000007：用户忘记密码
     * */
    @RequestMapping(value = "/1000007", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO forgetPwd(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.forgetPwd(userReqBO);
        } catch (Exception e) {
            log.error("========UserController---->用户忘记密码失败========", e);
        }
        return rsp;
    }

    /**
     * 1000010：用户修改信息
     * */
    @RequestMapping(value = "/1000010", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO updateAccount(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.updateAccount(userReqBO);
        } catch (Exception e) {
            log.error("========UserController---->用户忘记密码失败========", e);
        }
        return rsp;
    }
}
