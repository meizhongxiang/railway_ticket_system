package com.railwayTicketManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwayTicketManager.api.UserReqBO;
import com.railwayTicketManager.api.UserRspBO;
import com.railwayTicketManager.services.UserSercive;
import com.railwayTicketManager.utils.CookieUtils;
import com.railwayTicketManager.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
public class LoginController {

    @Autowired
    private UserSercive userSercive;

    /**
     * 1000001：用户密码登录
     * */
    @RequestMapping(value = "/1000001", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO loginByPwd(@RequestBody UserReqBO userReqBO,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        UserRspBO rsp = null;
        Date time =null;
        try {
            rsp = userSercive.login(userReqBO);
            time = new Date();
            String str = MD5Utils.md5Encrypt(rsp.getUserId().toString(), time.getTime());
            CookieUtils.writeCookie(response, "u_uid", str);
            JSONObject userBO = new JSONObject();
            userBO.put("userId", rsp.getUserId());
            userBO.put("accountId", rsp.getAccountId());
            userBO.put("loginDate", time);
            userBO.put("loginTime", time.getTime());
            userBO.put("sessionId", str);
            HttpSession session = request.getSession(true);
            session.setAttribute(str, userBO.toString());
            session.setMaxInactiveInterval(3 * 60 * 60);
        } catch (Exception e) {
            log.error("========LoginController---->用户密码登录失败========", e);
        }
        return rsp;
    }

    /**
     * 1000002：用户快捷登录
     * */
    @RequestMapping(value = "/1000002", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO loginByCode(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            rsp = userSercive.fastLogin(userReqBO);
        } catch (Exception e) {
            log.error("========LoginController---->用户快捷登录失败========", e);
        }
        return rsp;
    }

    /**
     * 1000009：用户退出登录
     * */
    @RequestMapping(value = "/1000009", method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    public UserRspBO cancelLogin(@RequestBody UserReqBO userReqBO) throws Exception{
        UserRspBO rsp = null;
        try {
            //rsp = userSercive.fastLogin(userReqBO);
        } catch (Exception e) {
            log.error("========LoginController---->用户退出登录失败========", e);
        }
        return rsp;
    }
}
